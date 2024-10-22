package com.unicorn.sg.demo.view;

import com.unicorn.sg.demo.client.camunda.CamundaRestService;
import com.unicorn.sg.demo.client.camunda.responses.CamundaHistoryProcessInstancesResponse;
import com.unicorn.sg.demo.domain.ProcessInstance;
import com.unicorn.sg.demo.view.service.ProcessInstanceService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.*;

@Named
@ViewScoped
public class TimeSeriesScrollerView implements Serializable {
    public static final Logger LOGGER = Logger.getLogger(TimeSeriesScrollerView.class.getName());

    Map<String, List<CamundaHistoryProcessInstancesResponse>> historicProcessInstances;
    transient List<ProcessInstance> processInstances;

    private ProcessInstance selectedProcessInstance;
    public static final String UNIFIED_PROCESS_NAME = "Unified Time Series Upload";

    @RestClient
    CamundaRestService restService;

    @PostConstruct
    public void init(){
        historicProcessInstances = restService.loadProcessInstancesHistory().stream().filter(p ->
                UNIFIED_PROCESS_NAME.equals(p.getProcessDefinitionName())).collect(groupingBy(CamundaHistoryProcessInstancesResponse::getBusinessKey, toList()));
        this.processInstances = ProcessInstanceService.getProcessInstnaces();
        LOGGER.log(Level.INFO, "{0} instances loaded", processInstances.size());
    }


    public List<ProcessInstance> getProcessInstances() {
        return processInstances;
    }

    public ProcessInstance getSelectedProcessInstance() {
        return selectedProcessInstance;
    }

    public void setSelectedProcessInstance(ProcessInstance selectedProcessInstance) {
        this.selectedProcessInstance = selectedProcessInstance;
    }

    public List<CamundaHistoryProcessInstancesResponse> loadProcessInstancesHistory(String businessKey){
       return historicProcessInstances.getOrDefault(businessKey, new ArrayList<>());
    }
}