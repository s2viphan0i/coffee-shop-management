package com.csm.model;

import lombok.Getter;

@Getter
public enum RegionEnum {
    ASIA("asia"),
    EU("eu"),
    US("usa");

    private String value;

    RegionEnum(String value) {
        this.value = value;
    }
}