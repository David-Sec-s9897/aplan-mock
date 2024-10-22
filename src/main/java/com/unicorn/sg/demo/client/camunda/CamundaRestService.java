package com.unicorn.sg.demo.client.camunda;

import com.unicorn.sg.demo.client.camunda.responses.CamundaCountResponse;
import com.unicorn.sg.demo.client.camunda.responses.CamundaHistoryProcessInstancesResponse;
import com.unicorn.sg.demo.client.camunda.responses.CamundaMetricsResponse;
import com.unicorn.sg.demo.client.component.CustomObjectMapperProvider;
import com.unicorn.sg.demo.client.camunda.responses.CamundaTaskResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;


@RegisterRestClient(baseUri = "http://37.221.248.104:8080/engine-rest")
@RegisterProvider(CustomObjectMapperProvider.class)
public interface CamundaRestService {

    @GET
    @Path("/task")
    @Consumes(MediaType.APPLICATION_JSON)
    List<CamundaTaskResponse> getTasks();

    @GET
    @Path("/task")
    @Consumes(MediaType.APPLICATION_JSON)
    List<CamundaTaskResponse> getTasksUnassigned(@QueryParam("unassigned") boolean unassigned);


    @GET
    @Path("history/process-instance")
    @Consumes(MediaType.APPLICATION_JSON)
    List<CamundaHistoryProcessInstancesResponse> loadProcessInstancesHistory();

    @GET
    @Path("/metrics")
    @Consumes(MediaType.APPLICATION_JSON)
    List<CamundaMetricsResponse> getMetrics();

    @GET
    @Path("process-instance/count")
    @Consumes(MediaType.APPLICATION_JSON)
    CamundaCountResponse countRunningProcessInstances();

    @GET
    @Path("incident/count")
    @Consumes(MediaType.APPLICATION_JSON)
    CamundaCountResponse countOpenIncidents();



}