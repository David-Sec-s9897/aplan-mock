package com.unicorn.sg.demo.client.monitoring;

import com.unicorn.sg.demo.client.component.CustomObjectMapperProvider;
import com.unicorn.sg.demo.client.monitoring.responses.MonitoringProcessesResponse;
import com.unicorn.sg.demo.client.transparency_portal.responses.TimeSeriesJobsResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;


@RegisterRestClient(baseUri = "https://tp-monitoring.secdavid.com/rest/")
@RegisterProvider(CustomObjectMapperProvider.class)
public interface EntsoeMonitoringRestService {

    @GET
    @Path("monitoring-rest/monitoring")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<MonitoringProcessesResponse> getMonitoring();
}