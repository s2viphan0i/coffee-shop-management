package com.csm.model;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");
    
    private String value;

    UserRoleEnum(String value) {
        this.value = value;
    }
}
