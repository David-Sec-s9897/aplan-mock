package com.unicorn.sg.demo.client;

import com.unicorn.sg.demo.client.camunda.responses.CamundaTaskResponse;
import com.unicorn.sg.demo.client.transparency_portal.TransparencyPortalRestService;
import com.unicorn.sg.demo.client.transparency_portal.responses.TimeSeriesJob;
import com.unicorn.sg.demo.client.transparency_portal.responses.TimeSeriesJobsResponse;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TransparencyPortalRestServiceTest {
    @RestClient
    TransparencyPortalRestService restService;

    @Test
    void restCallTest(){
        TimeSeriesJobsResponse timeSeriesJobs = restService.getTimeseriesList();
       System.out.println(timeSeriesJobs);
       assertFalse(timeSeriesJobs.getTimeSeriesJobs().isEmpty());
    }

    @Test
    void restStartProcess(){
        String businessKey = "2b924071-ea15-4937-b6b0-b61833579a5e";
        Response response = restService.startProcess(businessKey, true, null, null, null);
        System.out.println(response.getStatusInfo().getStatusCode());
        assertEquals(200, response.getStatusInfo().getStatusCode());
    }

    @Test
    void restUpdateCron(){
        String businessKey = "2b924071-ea15-4937-b6b0-b61833579a5e";
        Response response = restService.updateCron(businessKey, "0 30 12 ? * TUE");
        System.out.println(response.getStatusInfo().getStatusCode());
        assertEquals(200, response.getStatusInfo().getStatusCode());
    }
    @Test
    void restUpdateName(){
        String businessKey = "2b924071-ea15-4937-b6b0-b61833579a5e";
        Response response = restService.updateProcessName(businessKey, "17.1.C W A96 PoPBR asymmetric_updated");
        System.out.println(response.getStatusInfo().getStatusCode());
        assertEquals(200, response.getStatusInfo().getStatusCode());
    }
}