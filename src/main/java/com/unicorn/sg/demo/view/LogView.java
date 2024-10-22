package com.unicorn.sg.demo.view;

import com.unicorn.sg.demo.client.transparency_portal.TransparencyPortalRestService;
import com.unicorn.sg.demo.domain.*;
import com.unicorn.sg.demo.domain.anotations.Loggable;
import com.unicorn.sg.demo.repository.LogRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class LogView implements Serializable {
    public static final Logger LOGGER = Logger.getLogger(LogView.class.getName());

    @Inject
    LogRepository logRepository;
    private transient List<LogRecord> logs;
    private transient List<LogRecord> filteredLogs;

    private boolean globalFilterOnly;

    @RestClient
    transient TransparencyPortalRestService restService;


    @PostConstruct
    public void init() {
        this.logs = logRepository.listAll();
    }

    @Loggable
    @Transactional
    public void revertAction(LogRecord log) {
            switch (log.getType()) {
                case PROCESS_RENAME:
                    restService.updateProcessName(log.getTargetId(), log.getOldValue());
                    break;
                case PROCESS_UPDATE_CRON:
                    restService.updateCron(log.getTargetId(), log.getOldValue());
                    break;
            }
            log.setReverted(true);
            logRepository.setReverted(log.getId());


    }

    public List<LogRecord> getLogs() {
        return logs;
    }

    public List<LogRecord> getFilteredLogs() {
        return filteredLogs;
    }

    public void setFilteredLogs(List<LogRecord> filteredLogs) {
        this.filteredLogs = filteredLogs;
    }

    public boolean isGlobalFilterOnly() {
        return globalFilterOnly;
    }

    public void setGlobalFilterOnly(boolean globalFilterOnly) {
        this.globalFilterOnly = globalFilterOnly;
    }

    public List<String> getUsers() {
        return logs.stream().map(LogRecord::getUser).distinct().toList();
    }

}