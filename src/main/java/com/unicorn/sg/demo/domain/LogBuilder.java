package com.unicorn.sg.demo.domain;

public class LogBuilder {

    String targetId;
    String user;
    LogRecordType type;
    String description;
    boolean reversible = false;
    String oldValue;
    String newValue;

    public LogBuilder targetId(String targetId) {
        this.targetId = targetId;
        return this;
    }

    public LogBuilder user(String user) {
        this.user = user;
        return this;
    }

    public LogBuilder type(LogRecordType type) {
        this.type = type;
        return this;
    }

    public LogBuilder description(String description) {
        this.description = description;
        return this;
    }

    public LogBuilder reversible(boolean reversible) {
        this.reversible = reversible;
        return this;
    }

    public LogBuilder oldValue(String oldValue) {
        this.oldValue = oldValue;
        return this;
    }
    public LogBuilder newValue(String newValue) {
        this.newValue = newValue;
        return this;
    }
    public LogRecord build() {
        return new LogRecord(targetId, user, type, description, reversible, oldValue, newValue);
    }


}
