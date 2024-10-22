package com.unicorn.sg.demo.client.camunda.responses;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CamundaMetricsResponse implements Serializable {
    LocalDateTime timestamp;
    String name;
    String reporter;
    Integer value;


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CamundaMetricsResponse{" +
                "timestamp=" + timestamp +
                ", name='" + name + '\'' +
                ", reporter='" + reporter + '\'' +
                ", value=" + value +
                '}';
    }
}
