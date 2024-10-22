package com.unicorn.sg.demo.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class LogRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String targetId;
    LocalDateTime createTime;
    @Column(name = "userName")
    String user;
    String description;
    boolean reversible = false;
    boolean reverted = false;
    LocalDateTime lastModifiedTime;
    String oldValue;
    LogRecordType type;
    String newValue;

    public LogRecord(String targetId, String user, LogRecordType type, String description, boolean reversible, String oldValue, String newValue) {
        this.createTime = LocalDateTime.now();
        this.targetId= targetId;
        this.user = user;
        this.type = type;
       this.description = description;
        this.reversible = reversible;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public LogRecord() {

    }

    public static LogBuilder builder() {
        return new LogBuilder();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isReversible() {
        return reversible;
    }

    public void setReversible(boolean reversible) {
        this.reversible = reversible;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public boolean isReverted() {
        return reverted;
    }

    public void setReverted(boolean reverted) {
        this.reverted = reverted;
    }

    public LogRecordType getType() {
        return type;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}

