package com.unicorn.sg.demo.domain;

public class EventBuilder {
    String name;
    String overviewMessage;
    String description;
    String code;
    EventStaus status;

    public EventBuilder name(String name) {
        this.name = name;
        return this;
    }

    public EventBuilder overviewMessage(String overviewMessage) {
        this.overviewMessage = overviewMessage;
        return this;
    }

    public EventBuilder description(String description) {
        this.description = description;
        return this;
    }

    public EventBuilder code(String code) {
        this.code = code;
        return this;
    }

    public EventBuilder status(EventStaus status) {
        this.status = status;
        return this;
    }

    public Event build(){
        return new Event(name,overviewMessage,description,code,status);
    }




}
