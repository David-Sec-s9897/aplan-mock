package com.unicorn.sg.demo.rest;

import com.unicorn.sg.demo.model.Item;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Path("hello")
public class HelloResource {

    @Inject
    MeterRegistry registry;

    @Inject
    Template hello;

    @Path("/")
    @GET
    @Authenticated
    public String hello() {


        registry.counter("greeting_counter", Tags.of("name", "HELLO_WORLD!")).increment();
        return "Hello, World!";
    }

    @GET
    @Path("/web")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Test1", BigDecimal.ONE));
        itemList.add(new Item("Test2", BigDecimal.TEN));
        itemList.add(new Item("Test3", BigDecimal.valueOf(11L)));
        return hello.data("items", itemList);
    }
}
