package rest;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("hello")
public class HelloResource {

    @Inject
    MeterRegistry registry;

    @Path("/")
    @GET
    public String hello() {


        registry.counter("greeting_counter", Tags.of("name", "HELLO_WORLD!")).increment();
        return "Hello, World!";
    }
}
