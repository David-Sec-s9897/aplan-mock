package com.unicorn.sg.demo.view;

import com.unicorn.sg.demo.client.camunda.CamundaRestService;
import com.unicorn.sg.demo.client.camunda.responses.CamundaCountResponse;
import com.unicorn.sg.demo.client.camunda.responses.CamundaMetricsResponse;
import com.unicorn.sg.demo.client.camunda.responses.CamundaTaskResponse;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named
public class MetricsView implements Serializable {

    @RestClient
    private transient CamundaRestService restService;

    private transient List<CamundaMetricsResponse> metrics;
    private CamundaCountResponse runningProcessCount;
    private CamundaCountResponse openIncidentsCount;
    private transient List<CamundaTaskResponse> unassignedTasks;


    @PostConstruct
    public void init() {
        this.metrics = restService.getMetrics();
        runningProcessCount = restService.countRunningProcessInstances();
        openIncidentsCount = restService.countOpenIncidents();
        unassignedTasks = restService.getTasksUnassigned(true);
    }

    public List<CamundaMetricsResponse> getMetrics() {
        return metrics;
    }

    public CamundaMetricsResponse getMetric(String name) {
        return metrics.stream().filter(m -> m.getName().equals(name)).findFirst().orElseThrow();
    }


    public CamundaCountResponse getRunningProcessCount() {
        return runningProcessCount;
    }

    public CamundaCountResponse getOpenIncidentsCount() {
        return openIncidentsCount;
    }

    public List<CamundaTaskResponse> getUnassignedTasks() {
        return unassignedTasks;
    }
}
