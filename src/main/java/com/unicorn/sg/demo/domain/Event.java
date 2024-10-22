package com.unicorn.sg.demo.domain;

import java.io.Serializable;

public class Event implements Serializable {
    String name;
    String overviewMessage;
    String description;
    String code;
    EventStaus status;

    protected Event(String name, String overviewMessage, String description, String code, EventStaus status) {
        this.name = name;
        this.overviewMessage = overviewMessage;
        this.description = description;
        this.code = code;
        this.status = status;
    }

    public static EventBuilder builder(){
        return new EventBuilder();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public String getCode() {
        return code;
    }

    public EventStaus getStatus() {
        return status;
    }

    public String getOverviewMessage() {
        return overviewMessage;
    }
}
