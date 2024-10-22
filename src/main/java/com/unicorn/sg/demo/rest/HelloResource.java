package com.unicorn.sg.demo.rest;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;




@Path("hello")
public class HelloResource {

    @Inject
    MeterRegistry registry;

    @Path("/")
    @GET
    @Authenticated
    public String hello() {


        registry.counter("greeting_counter", Tags.of("name", "HELLO_WORLD!")).increment();
        return "Hello, World!";
    }
}
