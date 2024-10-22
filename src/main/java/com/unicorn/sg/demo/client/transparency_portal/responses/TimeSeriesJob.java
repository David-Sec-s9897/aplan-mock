package com.unicorn.sg.demo.client.transparency_portal.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSeriesJob implements Serializable {

    private String timeSeriesName;
    private String timeSeriesCategory;
    private String mergeType;
    private String cronExpression;
    private List<String> timeseriesKeys;
    private Long startTime;
    private String startCron;
    private String duration;
    private Long lastStart;
    private Long nextStart;
    private String active;
    private String state;
    private String businessKey;
    private Boolean suspended;
    private Boolean fileUpload;
    private String datasource;
    private Integer lastMappingVersion;
    private List<Integer> mappingVersionList;
    private Integer lastMasterDataVersion;
    private List<Integer> masterDataVersionList;
    private String addTime;
    private Boolean weeksInMonthRecognizable;
    private Integer maximumRetryCount;
    private String offset;
    private String offsetDuration;
    private String retryDelay;

    public String getTimeSeriesName() {
        return timeSeriesName;
    }

    public void setTimeSeriesName(String timeSeriesName) {
        this.timeSeriesName = timeSeriesName;
    }

    public String getTimeSeriesCategory() {
        return timeSeriesCategory;
    }

    public void setTimeSeriesCategory(String timeSeriesCategory) {
        this.timeSeriesCategory = timeSeriesCategory;
    }

    public String getMergeType() {
        return mergeType;
    }

    public void setMergeType(String mergeType) {
        this.mergeType = mergeType;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public List<String> getTimeseriesKeys() {
        return timeseriesKeys;
    }

    public void setTimeseriesKeys(List<String> timeseriesKeys) {
        this.timeseriesKeys = timeseriesKeys;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getStartCron() {
        return startCron;
    }

    public void setStartCron(String startCron) {
        this.startCron = startCron;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getLastStart() {
        return lastStart;
    }

    public void setLastStart(Long lastStart) {
        this.lastStart = lastStart;
    }

    public Long getNextStart() {
        return nextStart;
    }

    public void setNextStart(Long nextStart) {
        this.nextStart = nextStart;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    public Boolean getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Boolean fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public Integer getLastMappingVersion() {
        return lastMappingVersion;
    }

    public void setLastMappingVersion(Integer lastMappingVersion) {
        this.lastMappingVersion = lastMappingVersion;
    }

    public List<Integer> getMappingVersionList() {
        return mappingVersionList;
    }

    public void setMappingVersionList(List<Integer> mappingVersionList) {
        this.mappingVersionList = mappingVersionList;
    }

    public Integer getLastMasterDataVersion() {
        return lastMasterDataVersion;
    }

    public void setLastMasterDataVersion(Integer lastMasterDataVersion) {
        this.lastMasterDataVersion = lastMasterDataVersion;
    }

    public List<Integer> getMasterDataVersionList() {
        return masterDataVersionList;
    }

    public void setMasterDataVersionList(List<Integer> masterDataVersionList) {
        this.masterDataVersionList = masterDataVersionList;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Boolean getWeeksInMonthRecognizable() {
        return weeksInMonthRecognizable;
    }

    public void setWeeksInMonthRecognizable(Boolean weeksInMonthRecognizable) {
        this.weeksInMonthRecognizable = weeksInMonthRecognizable;
    }

    public Integer getMaximumRetryCount() {
        return maximumRetryCount;
    }

    public void setMaximumRetryCount(Integer maximumRetryCount) {
        this.maximumRetryCount = maximumRetryCount;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getOffsetDuration() {
        return offsetDuration;
    }

    public void setOffsetDuration(String offsetDuration) {
        this.offsetDuration = offsetDuration;
    }

    public String getRetryDelay() {
        return retryDelay;
    }

    public void setRetryDelay(String retryDelay) {
        this.retryDelay = retryDelay;
    }

    @Override
    public String toString() {
        return "TimeSeriesJob{" +
                "timeSeriesName='" + timeSeriesName + '\'' +
                ", timeSeriesCategory='" + timeSeriesCategory + '\'' +
                ", mergeType='" + mergeType + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", timeseriesKeys=" + timeseriesKeys +
                ", startTime=" + startTime +
                ", startCron='" + startCron + '\'' +
                ", duration='" + duration + '\'' +
                ", lastStart=" + lastStart +
                ", nextStart=" + nextStart +
                ", active='" + active + '\'' +
                ", state='" + state + '\'' +
                ", businessKey='" + businessKey + '\'' +
                ", suspended=" + suspended +
                ", fileUpload=" + fileUpload +
                ", datasource='" + datasource + '\'' +
                ", lastMappingVersion=" + lastMappingVersion +
                ", mappingVersionList=" + mappingVersionList +
                ", lastMasterDataVersion=" + lastMasterDataVersion +
                ", masterDataVersionList=" + masterDataVersionList +
                ", addTime='" + addTime + '\'' +
                ", weeksInMonthRecognizable=" + weeksInMonthRecognizable +
                ", maximumRetryCount=" + maximumRetryCount +
                ", offset='" + offset + '\'' +
                ", offsetDuration='" + offsetDuration + '\'' +
                ", retryDelay='" + retryDelay + '\'' +
                '}';
    }
}
