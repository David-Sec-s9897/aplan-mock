package com.unicorn.sg.demo.view.domain;

public enum TimeLineEventType {

    AVAILABLE("Available","available"),
    UNAVAILABLE("Unavailable", "unavailable");

    String styleClass;
    String name;

    TimeLineEventType(String name, String styleClass) {
        this.name = name;
        this.styleClass = styleClass;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public String getName() {
        return name;
    }
}