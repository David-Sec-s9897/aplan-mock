package com.unicorn.sg.demo.view;

import com.unicorn.sg.demo.view.service.AvatarService;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@SessionScoped
@Named
public class UserManager implements Serializable {
    @Inject
    SecurityIdentity identity;

    @Inject
    AvatarService avatarService;
    public static final String[] avatars = {"amyelsner.png", "annafali.png", "asiyajavayant.png",
            "bernardodominic.png", "elwinsharvill.png",
            "ionibowcher.png",
            "ivanmagalhaes.png",
            "onyamalimba.png",
            "profile.jpg",
            "stephenshaw.png",
            "xuxuefeng.png"};


    public String getUserName(){
        return identity.getPrincipal().getName();
    }

    public String getAvatar(){
        return avatarService.getAvatar(identity.getPrincipal().getName());

    }

    public void logout(){
        identity = null;
    }

}
