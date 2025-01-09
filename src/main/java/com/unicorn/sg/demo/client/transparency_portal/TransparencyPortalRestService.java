package com.unicorn.sg.demo.client.transparency_portal;

import com.unicorn.sg.demo.client.component.CustomObjectMapperProvider;
import com.unicorn.sg.demo.client.transparency_portal.requests.TransformRequest;
import com.unicorn.sg.demo.client.transparency_portal.responses.TimeSeriesJobsResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient(baseUri = "http://37.221.248.104:8080/logic-mapper/rest/")
@RegisterProvider(CustomObjectMapperProvider.class)
public interface TransparencyPortalRestService {

    @GET
    @Path("timeseries/job")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    TimeSeriesJobsResponse getTimeseriesList();


    @POST
    @Path("timeseries/process/{processBusinessKey}/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response startProcess(@PathParam("processBusinessKey") String processBusinessKey,
                          @QueryParam("automatic") @DefaultValue("false") boolean automatic,
                          @QueryParam("startTime") String startTime,
                          @QueryParam("endTime") String endTime,
                          @QueryParam("userSetRevisionNumber") String userSetRevisionNumber);

    @PUT
    @Path("timeseries/job/{processBusinessKey}/cron")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response updateCron(@PathParam("processBusinessKey") String processBusinessKey,
                        @RequestBody String cronExpression);


    @PUT
    @Path("timeseries/job/{processBusinessKey}/name")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateProcessName(@PathParam("processBusinessKey") String processBusinessKey,
                               String timeSeriesName);
    @PUT
    @Path("xsltTransformation/transform")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response xsltTransformation(TransformRequest transformRequest);
}