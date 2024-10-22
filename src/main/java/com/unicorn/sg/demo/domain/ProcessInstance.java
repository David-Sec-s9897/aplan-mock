package com.unicorn.sg.demo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

public class ProcessInstance implements Serializable {

    String id;
    LocalDateTime startTime;
    LocalDateTime endTime;
    LocalDateTime intervalStart;
    LocalDateTime intervalEnd;
    ProcessStatus status;

    public ProcessInstance(String uuid, LocalDateTime minus, ProcessStatus processStatus) {
        Random random = new Random();
        this.id = uuid;
        this.startTime = minus;
        this.intervalStart = startTime.minusDays(1).withHour(0).withMinute(0).withSecond(0) ;
        this.intervalEnd = intervalStart.plusDays(1).withHour(0).withMinute(0).withSecond(0) ;
        this.endTime = minus.plusMinutes(random.nextInt(60));

        this.status = processStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public ProcessStatus getStatus() {
        return status;
    }

    public LocalDateTime getIntervalStart() {
        return intervalStart;
    }

    public LocalDateTime getIntervalEnd() {
        return intervalEnd;
    }

    public void setStatus(ProcessStatus status) {
        this.status = status;
    }
}
