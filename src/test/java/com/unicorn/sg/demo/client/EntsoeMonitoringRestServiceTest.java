package com.unicorn.sg.demo.client;

import com.unicorn.sg.demo.client.monitoring.EntsoeMonitoringRestService;
import com.unicorn.sg.demo.client.monitoring.responses.MonitoringProcessesResponse;
import com.unicorn.sg.demo.client.transparency_portal.responses.TimeSeriesJobsResponse;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
class EntsoeMonitoringRestServiceTest {
    @RestClient
    EntsoeMonitoringRestService restService;

    @Test
    void restCallTest() {
        List<MonitoringProcessesResponse> response = restService.getMonitoring();
        System.out.println(response);
        assertFalse(response.isEmpty());
    }


}