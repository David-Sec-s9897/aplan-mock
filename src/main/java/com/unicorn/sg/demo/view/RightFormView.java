package com.unicorn.sg.demo.view;

import com.unicorn.sg.demo.client.camunda.CamundaRestService;
import com.unicorn.sg.demo.client.camunda.responses.CamundaTaskResponse;
import io.smallrye.jwt.auth.cdi.ClaimValueProducer;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class RightFormView implements Serializable {
    public static final Logger LOGGER = Logger.getLogger(RightFormView.class.getName());
    public static final String CAMUNDA_BASE_URL="http://37.221.248.104:8080/logic-mapper/{:formName}?taskId={:taskId}&callbackUrl={:callback}";

    @Inject
    UserManager userManager;
    @Inject
    ClaimValueProducer claimValueProducer;

    private transient List<CamundaTaskResponse> camundaTasks;

    @RestClient
    transient CamundaRestService restService;


    @PostConstruct
    public void init() {
        this.camundaTasks = restService.getTasks().stream().sorted((o1, o2) -> o2.getCreated().compareTo(o1.getCreated())).toList();
    }


    public List<CamundaTaskResponse> getCamundaTasks() {
        return camundaTasks;
    }

    public String notAssignedToMe(String assignee)
    {
        if (assignee == null || assignee.isEmpty()) {
            return "";
        }
        else{
        return !userManager.getUserName().equalsIgnoreCase(assignee)? "done":"";
        }
    }

    public String redirectToTask(CamundaTaskResponse task){
        String form = task.getFormKey().replace("app:", "");
        String callbackUrl = "http://localhost:8080/";
        return CAMUNDA_BASE_URL.replace("{:formName}",form)
                .replace("{:taskId}", task.getId())
                .replace("{:callback}",callbackUrl);

    }
}