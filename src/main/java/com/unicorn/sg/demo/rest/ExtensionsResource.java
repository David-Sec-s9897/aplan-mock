package com.unicorn.sg.demo.rest;

import io.micrometer.core.instrument.MeterRegistry;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import com.unicorn.sg.demo.ExtensionsService;

import java.nio.charset.StandardCharsets;

@Path("/outages")
public class ExtensionsResource {

    @Inject
    MeterRegistry registry;

    @RestClient
    ExtensionsService extensionsService;

    @GET
    @Path("/test")
    @Authenticated
    public String testCall() {
        registry.counter("test_process_start").increment();

        try (Response response = extensionsService.sendOutage(ProcessUtils.createTestProcess().getBytes(StandardCharsets.UTF_8))) {
            return response.getStatusInfo().toEnum().name();
        }
    }
}
