package com.saferent.domain.enums;

public enum RoleType {

    ROLE_CUSTOMER("Customer"),   //"Customer"
    ROLE_ADMIN("Administrator");

    private String name;

    private RoleType(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}
