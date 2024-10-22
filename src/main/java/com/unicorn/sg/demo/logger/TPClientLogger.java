package com.unicorn.sg.demo.logger;

import com.unicorn.sg.demo.domain.anotations.Loggable;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.quarkus.arc.Unremovable;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jboss.resteasy.reactive.client.api.ClientLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Unremovable
public class TPClientLogger implements ClientLogger {
    public static final Logger LOGGER = Logger.getLogger(TPClientLogger.class.getName());

    @Inject
    Tracer tracer;

    int bodySize;

    @Override
    public void setBodySize(int bodySize) {
        this.bodySize = bodySize;
    }

    @Override
    @Loggable
    public void logResponse(HttpClientResponse response, boolean redirect) {
        LOGGER.log(Level.INFO, ()-> "RESPONSE : (HTTP " +
                response.statusCode() + ") " +
                "<-- " + response.request().absoluteURI());
        response.bodyHandler(body -> {
            String responseBody = body.toString();  // Convert Buffer to String
            LOGGER.log(Level.INFO, () -> "Response Body: " + responseBody);
            Span span = tracer.spanBuilder("Response - " + response.request().absoluteURI()).startSpan();
            span.setAttribute("request", "RESPONSE --> " + response.request().absoluteURI());
            span.setAttribute("responseBody", responseBody);
            span.setAttribute("statusCode", response.statusCode());
            span.end();


        });
    }

    @Override
    @Loggable
    public void logRequest(HttpClientRequest request, Buffer body, boolean omitBody) {
        LOGGER.log(Level.INFO, ()-> "REQUEST --> " + request.absoluteURI());
        Span span = tracer.spanBuilder("Request - " + request.absoluteURI()).startSpan();
        span.setAttribute("request", "REQUEST --> " + request.absoluteURI());
        span.end();
    }
}
