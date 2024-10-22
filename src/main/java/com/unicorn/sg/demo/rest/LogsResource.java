package com.unicorn.sg.demo.rest;

import io.micrometer.core.instrument.MeterRegistry;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.unicorn.sg.demo.model.Item;

import java.math.BigDecimal;

@Path("logs")
public class LogsResource {

    @Inject
    MeterRegistry registry;

    @Inject
    Template logs;

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return logs.data("item", new Item("Test2", BigDecimal.TEN));
    }
}
