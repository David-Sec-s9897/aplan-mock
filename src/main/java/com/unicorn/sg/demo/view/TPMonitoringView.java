package com.unicorn.sg.demo.view;

import com.unicorn.sg.demo.client.monitoring.EntsoeMonitoringRestService;
import com.unicorn.sg.demo.client.monitoring.responses.MonitoringProcessesResponse;
import com.unicorn.sg.demo.view.domain.TimeLineEventType;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;


@ViewScoped
@Named("customTimelineView")
public class TPMonitoringView implements Serializable {
    @RestClient
    EntsoeMonitoringRestService restService;


    private TimelineModel<String, ?> model;

    private LocalDateTime start;
    private LocalDateTime end;

    private long missingIntervalsCont;
    private long monitoredProcessesCont;


    @PostConstruct
    public void init() {
        model = new TimelineModel<>();
        missingIntervalsCont = 0;
        start = LocalDate.now().minusDays(1).atStartOfDay();
        end = LocalDate.now().plusDays(1).atStartOfDay();
        List<MonitoringProcessesResponse> processes = restService.getMonitoring();
        processes.forEach(process -> missingIntervalsCont += process.getMissingIntervals().size());
        monitoredProcessesCont = processes.size();
        System.out.println("processes.size()" + processes.size());
        for (MonitoringProcessesResponse process : processes) {
            process.getAvailableIntervals().forEach(interval -> {
                TimelineEvent event = buildTimeLineEvent(TimeLineEventType.AVAILABLE, interval.start, interval.end, process.getName());
                model.add(event);
            });
            process.getMissingIntervals().forEach(interval -> {
                TimelineEvent event = buildTimeLineEvent(TimeLineEventType.UNAVAILABLE, interval.start, interval.end, process.getName());
                model.add(event);
            });
        }
    }

    private TimelineEvent<Object> buildTimeLineEvent(TimeLineEventType timelineEventType, ZonedDateTime start, ZonedDateTime end, String name) {
        return TimelineEvent.builder()
                .data(formatMessage(timelineEventType, start, end))
                .startDate(start.toLocalDateTime())
                .endDate(end.toLocalDateTime())
                .editable(false)
                .group(name)
                .styleClass(timelineEventType.getStyleClass())
                .build();
    }


    public void onSelect(TimelineSelectEvent<String> e) {
        TimelineEvent<String> timelineEvent = e.getTimelineEvent();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "selected", timelineEvent.getData()));
    }

    private static String formatMessage(TimeLineEventType timelineEventType, ZonedDateTime start, ZonedDateTime end) {
        return String.format("%s %s - %s", timelineEventType.getName(),
                start,
                end);
    }

    public TimelineModel<String, ?> getModel() {
        return model;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public long getMissingIntervalsCont() {
        return missingIntervalsCont;
    }

    public long getMonitoredProcessesCont() {
        return monitoredProcessesCont;
    }
}
