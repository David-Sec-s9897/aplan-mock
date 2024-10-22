package com.unicorn.sg.demo.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.unicorn.sg.demo.domain.anotations.Loggable;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Level;
import java.util.logging.Logger;

@Loggable
@Interceptor
public class LogExecutionInterceptor {

    @Inject
    Tracer tracer;

    private static final Logger LOGGER = Logger.getLogger(LogExecutionInterceptor.class.getName());

    @AroundInvoke
    public Object logMethodExecution(InvocationContext context) throws Exception {
        // Before method execution
        Span span = tracer.spanBuilder(context.getMethod().getName()).startSpan();
        LOGGER.info("Starting method: " + context.getMethod().getName());
        try {
            logMethod("Starting method", context);
            // Proceed with the original method call
            return context.proceed();
        }
        finally {
            // After method execution

            String msg= logMethod("Finished method", context);
            span.setAttribute("logs",msg);
            span.end();
        }
    }

    // Helper method to handle logging
    private String logMethod(String message, InvocationContext context) {
        // Log the method name and its parameters
        StringBuilder logMessage = new StringBuilder(message + ": " + context.getMethod().getName());
        Object[] parameters = context.getParameters();

        if (parameters != null && parameters.length > 0) {
            logMessage.append(" with parameters: ");
            for (Object param : parameters) {
                logMessage.append(param).append(", ");
            }
        } else {
            logMessage.append(" with no parameters.");
        }

        // Log the final message

        LOGGER.info(logMessage::toString);
        return logMessage.toString();
    }
}