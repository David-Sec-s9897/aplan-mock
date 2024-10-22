package com.unicorn.sg.demo.client;

import com.unicorn.sg.demo.client.camunda.CamundaRestService;
import com.unicorn.sg.demo.client.camunda.responses.CamundaCountResponse;
import com.unicorn.sg.demo.client.camunda.responses.CamundaHistoryProcessInstancesResponse;
import com.unicorn.sg.demo.client.camunda.responses.CamundaMetricsResponse;
import com.unicorn.sg.demo.client.camunda.responses.CamundaTaskResponse;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@QuarkusTest
class CamundaRestServiceTest {
    @RestClient
    CamundaRestService restService;

    @Test
    void testCallTest(){
       List<CamundaTaskResponse> taskResponses = restService.getTasks();
       System.out.println(taskResponses);
       Assertions.assertFalse(taskResponses.isEmpty());
    }
    @Test
    void testCallTasksUnassigned(){
       List<CamundaTaskResponse> tasksUnassigned = restService.getTasksUnassigned(true);
       System.out.println(tasksUnassigned);
       Assertions.assertFalse(tasksUnassigned.isEmpty());
    }

    @Test
    void testCallProcessInstancesHistoryTest(){
       List<CamundaHistoryProcessInstancesResponse> historyInstances = restService.loadProcessInstancesHistory();
       System.out.println(historyInstances);
       Assertions.assertFalse(historyInstances.isEmpty());
        Map<String, List<CamundaHistoryProcessInstancesResponse>> list = historyInstances.stream().filter(p -> "Unified Time Series Upload".equals(p.getProcessDefinitionName())).collect(groupingBy(CamundaHistoryProcessInstancesResponse::getBusinessKey, toList()));
        System.out.println(list);
    }



    @Test
    void testCallMetrics(){
        List<CamundaMetricsResponse> taskResponses = restService.getMetrics();
        System.out.println(taskResponses);
        Assertions.assertFalse(taskResponses.isEmpty());
    }

    @Test
    void testRunningProcessInstances(){
        CamundaCountResponse count = restService.countRunningProcessInstances();
        System.out.println(count);
        Assertions.assertNotNull(count);
    }
}