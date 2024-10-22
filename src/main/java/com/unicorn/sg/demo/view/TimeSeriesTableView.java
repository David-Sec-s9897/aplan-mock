package com.unicorn.sg.demo.view;

import com.unicorn.sg.demo.client.transparency_portal.TransparencyPortalRestService;
import com.unicorn.sg.demo.client.transparency_portal.responses.TimeSeriesJob;
import com.unicorn.sg.demo.domain.*;
import com.unicorn.sg.demo.domain.anotations.Loggable;
import com.unicorn.sg.demo.repository.LogRepository;
import com.unicorn.sg.demo.utils.DateTimeUtils;
import com.unicorn.sg.demo.view.service.EventService;
import com.unicorn.sg.demo.view.service.dto.TPStartProcessRequest;
import it.burning.cron.CronExpressionDescriptor;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIData;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Named
@ViewScoped
public class TimeSeriesTableView implements Serializable {
    public static final Logger LOGGER = Logger.getLogger(TimeSeriesTableView.class.getName());

    private static final String NON_CRON_BASED_TIME_SERIES = "No Cron needed";
    private static final String VALIDATION_ERROR = "Validation Error";

    public static final String CAMUNDA_TASKS_BASE_URL = "http://37.221.248.104:8080/camunda/app/cockpit/default/#/process-instance/{taskId}/runtime";

    private transient List<TimeSeriesJob> timeSeriesJobs;
    private TimeSeriesJob selectedTimeSeriesJob;
    private ProcessInstance selectedProcessInstance;
    TPStartProcessRequest startProcessRequest;

    private transient List<Event> events;

    private transient List<Event> failedEvents;

    transient UIData chronolineData;

    @Inject
    DateTimeUtils dateTimeUtils;

    @Inject
    LogRepository logRepository;

    @Inject
    UserManager userManager;

    @RestClient
    transient TransparencyPortalRestService restClient;

    @PostConstruct
    public void init() {
        events = EventService.getOKEvents();
        failedEvents = EventService.getFailedEvents();
        timeSeriesJobs = restClient.getTimeseriesList().getTimeSeriesJobs();
        this.startProcessRequest = new TPStartProcessRequest();

    }

    public Border getBordersFromName(String tsName) {
        Pattern pattern = Pattern.compile("\\b(FR|CH|D|IT|AT|A)\\b(?: \\b(FR|CH|D|IT|AT|A)\\b)?");

        Matcher matcher = pattern.matcher(tsName);
        if (matcher.find()) {
            String countryCode1 = matcher.group(1);
            String countryCode2 = matcher.group(2);  // May be null if no second country code is found
            return new Border(countryCode1, countryCode2);
        }
        return new Border();
    }

    public String getStatus(TimeSeriesJob ts) {
        long lastStart = ts.getLastStart() == null ? Long.MAX_VALUE : ts.getLastStart();
        long now = System.currentTimeMillis();
        if (now > lastStart) {
            return "delivered";
        }
        if (now < lastStart) {
            if (Boolean.TRUE.equals(ts.getSuspended())) {
                return "suspended";
            }
            return "pending";
        } else {
            return "failed";
        }
    }

    /**
     * This Method formats a cron expresssion to a human-readable String.
     *
     * @param cronExpression as a cron expression (* * * * *) in a {@link String}
     * @param disabled       specify if the cron is needed
     * @return a readable {@link String}
     */
    public String formatCron(String cronExpression, boolean disabled) {
        try {
            if (disabled) {
                return NON_CRON_BASED_TIME_SERIES;
            } else if (!StringUtils.isBlank(cronExpression)) {
                return CronExpressionDescriptor.getDescription(cronExpression);
            }
        } catch (Exception e) {
            LOGGER.log(Level.INFO, String.format("Parsing cron '%s' failed.", cronExpression), e);
        }
        return "";
    }


    public void onCellEdit(CellEditEvent<String> event) {
        String newValue = event.getNewValue();
        String oldValue = event.getOldValue();
        TimeSeriesJob job = (TimeSeriesJob) ((DataTable) event.getComponent()).getRowData();
        String columnName = event.getColumn().getHeaderText();

        if (columnName.equalsIgnoreCase("Name")) {
            updateName(job, oldValue, newValue);
        }
        else if (columnName.equalsIgnoreCase("Cron")) {
            updateCron(job, oldValue, newValue);
        }

    }

    @Loggable
    @Transactional
    public void updateCron(TimeSeriesJob job, String oldValue, String newValue) {
        LogRecord log = LogRecord.builder().targetId(job.getBusinessKey()).user(userManager.getUserName()).type(LogRecordType.PROCESS_UPDATE_CRON).description("Updated process CRON" + job.getTimeSeriesName())
                .oldValue(oldValue).newValue(newValue).reversible(true).build();
        logRepository.persist(log);
        restClient.updateCron(job.getBusinessKey(), newValue);

    }
    @Loggable
    @Transactional
    public void updateName(TimeSeriesJob job, String oldValue, String newValue) {
        LogRecord log = LogRecord.builder().targetId(job.getBusinessKey()).user(userManager.getUserName()).type(LogRecordType.PROCESS_RENAME).description("Updated process CRON for process " + job.getTimeSeriesName())
                .oldValue(oldValue).newValue(newValue).reversible(true).build();
        logRepository.persist(log);
        restClient.updateProcessName(job.getBusinessKey(), newValue);
    }

    public void isCronExpressionValid(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        if (ctx != null && component != null) {
            LOGGER.log(Level.FINE, "Context: {0}, Component: {1}", new Object[]{ctx, component});
        }
        String cronExpression = value.toString();
        if (cronExpression == null || cronExpression.isEmpty()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, VALIDATION_ERROR,
                    "There is nothing in the field. Please insert your prefered cron expression."));
        }
        if (cronExpression.startsWith("*")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, VALIDATION_ERROR,
                    "Do not configure a job with a * as starting argument. We should not start a process every second."));
        }
        try {
            new CronExpressionDescriptor(cronExpression);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, String.format("TimeSeriesJobController::isCronExpressionValid %s isn't a valid input.", cronExpression), e);
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, VALIDATION_ERROR,
                    cronExpression + " isn't a valid input."));
        }
    }

    public String redirectToCamunda(String taskId) {
        LOGGER.log(Level.INFO, "redirecting task id {0}", taskId);
        taskId = "8195e77d-8bd3-11ef-a305-0242b0426a79"; // demo task id
        return CAMUNDA_TASKS_BASE_URL.replace("{taskId}", taskId) + "?faces-redirect=true";
    }

    public List<TimeSeriesJob> getTimeSeriesJobs() {
        return timeSeriesJobs;
    }

    public List<Event> getEvents() {
        if (this.selectedProcessInstance != null && ProcessStatus.delivered.equals(this.selectedProcessInstance.getStatus())) {
            return events;
        } else {
            return failedEvents;
        }
    }

    @Transactional
    public void handleStartProcess(TimeSeriesJob job) {
        try {
            restClient.startProcess(job.getBusinessKey(), true, null, null, null);
            LogRecord log = LogRecord.builder().targetId(job.getBusinessKey()).user(userManager.getUserName()).type(LogRecordType.PROCESS_START_AUTOMATICALLY).description("Started automatic process " + job.getTimeSeriesName()).build();
            logRepository.persist(log);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Process started.", "The process " + job.getTimeSeriesName() + " has been started."));
        } catch (Exception be) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot start Process.", "The TimeSeries " + job.getTimeSeriesName() +
                            " cannot be started. Please control the connection."));
        }
    }

    @Transactional
    @Loggable
    public void handleStartManualProcess() {
        try {
            startTPProcess(selectedTimeSeriesJob.getBusinessKey(), false, startProcessRequest.getStartTime(),
                    startProcessRequest.getEndTime(), String.valueOf(startProcessRequest.getRevisionNumber()));
            LogRecord log = LogRecord.builder().targetId(selectedTimeSeriesJob.getBusinessKey()).user(userManager.getUserName()).type(LogRecordType.PROCESS_START_MANUALY).description("Started manually process " + selectedTimeSeriesJob.getTimeSeriesName())
                    .newValue(startProcessRequest.getStartTime().format(dateTimeUtils.getTpDateFormat()) + " - " + startProcessRequest.getEndTime().format(dateTimeUtils.getTpDateFormat()))
                    .build();
            logRepository.persist(log);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Process started.", "The process " + selectedTimeSeriesJob.getTimeSeriesName() + " has been started."));
        } catch (Exception be) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot start Process.", "The TimeSeries " + selectedTimeSeriesJob.getTimeSeriesName() +
                            " cannot be started. Please control the connection."));
        }
    }

    @Loggable
    public Response startTPProcess(String businessKey, boolean automatic, LocalDateTime startTime, LocalDateTime endTime, String userSetRevisionNumber) {
        return restClient.startProcess(businessKey, automatic, dateTimeUtils.format(startTime, dateTimeUtils.getTpDateFormat()),
                dateTimeUtils.format(endTime, dateTimeUtils.getTpDateFormat()),
                userSetRevisionNumber);
    }

    public UIData getChronolineData() {
        return chronolineData;
    }

    public void setChronolineData(UIData chronolineData) {
        this.chronolineData = chronolineData;
    }

    public ProcessInstance getSelectedProcessInstance() {
        return selectedProcessInstance;
    }

    public void setSelectedProcessInstance(ProcessInstance selectedProcessInstance) {
        this.selectedProcessInstance = selectedProcessInstance;
    }

    public void setSelectedTimeSeriesJob(TimeSeriesJob job) {
        this.selectedTimeSeriesJob = job;
    }

    public TimeSeriesJob getSelectedTimeSeriesJob() {
        return selectedTimeSeriesJob;
    }

    public TPStartProcessRequest getStartProcessRequest() {
        return startProcessRequest;
    }
}