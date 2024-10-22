package com.unicorn.sg.demo.client.monitoring.responses;

import java.util.List;

public class MonitoringProcessesResponse {
    String name;
    List<TimeInterval> availableIntervals;
    List<TimeInterval> missingIntervals;


    public String getName() {
        return name;
    }

    public List<TimeInterval> getAvailableIntervals() {
        return availableIntervals;
    }

    public List<TimeInterval> getMissingIntervals() {
        return missingIntervals;
    }

    @Override
    public String toString() {
        return "MorintoringProcessesResponse{" +
                "name='" + name + '\'' +
                ", availableIntervals=" + availableIntervals +
                ", missingIntervals=" + missingIntervals +
                '}';
    }
}
