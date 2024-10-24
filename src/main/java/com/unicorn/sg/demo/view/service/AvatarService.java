package com.unicorn.sg.demo.view.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class AvatarService {

    private Map<String, String> avatarsMap;

    private static final  String defaultAvatar = "avatar.png";


    @PostConstruct
    public void init() {
        avatarsMap = new HashMap<>();
        avatarsMap.put("alice", "ionibowcher.png");
        avatarsMap.put("bob", "amyelsner.png");
        avatarsMap.put("david", "david.png");
    }


    public String getAvatar(String name) {
        return avatarsMap.getOrDefault(name, defaultAvatar);
    }
}
