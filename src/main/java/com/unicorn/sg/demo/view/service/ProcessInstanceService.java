package com.unicorn.sg.demo.view.service;

import com.unicorn.sg.demo.domain.ProcessInstance;
import com.unicorn.sg.demo.domain.ProcessStatus;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ProcessInstanceService {

    private ProcessInstanceService(){
        throw new IllegalStateException("Utility class");
    }

    static Random random = new Random();

    public static List<ProcessInstance> getProcessInstnaces() {
        List<ProcessInstance> processInstances = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            processInstances.add(new ProcessInstance(UUID.randomUUID().toString(), LocalDateTime.now().minus(random.nextInt(600), ChronoUnit.HOURS), random.nextInt(10)>1?ProcessStatus.delivered:ProcessStatus.failed));
        }
        return processInstances;
    }

}
