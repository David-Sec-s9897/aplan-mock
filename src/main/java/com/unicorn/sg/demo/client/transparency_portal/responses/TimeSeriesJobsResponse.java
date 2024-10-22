package com.unicorn.sg.demo.client.transparency_portal.responses;

import java.util.List;

public class TimeSeriesJobsResponse {

    private List<TimeSeriesJob> timeSeriesJobs;

    // Getters and Setters

    public List<TimeSeriesJob> getTimeSeriesJobs() {
        return timeSeriesJobs;
    }

    public void setTimeSeriesJobs(List<TimeSeriesJob> timeSeriesJobs) {
        this.timeSeriesJobs = timeSeriesJobs;
    }
}
