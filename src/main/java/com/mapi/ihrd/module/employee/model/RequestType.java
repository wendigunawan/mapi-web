package com.mapi.ihrd.module.employee.model;

public enum RequestType {

    LEAVE("leave"),
    SICK("sick"),
    PERMISSION("permission"),
    OVERTIME("permission"),
    LATE("late");

    private String val;

    private RequestType(String val) {
        this.val = val;
    }
}
