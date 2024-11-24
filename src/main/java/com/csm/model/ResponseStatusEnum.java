package com.csm.model;

public enum ResponseStatusEnum {
    SUCCESS("030000", "Success"),
    GENERAL_ERROR("030001", "Any error occur"),
    INVALID_DATA_TYPE("030002", "Field %s is in invalid data type"),
    USERNAME_ALREADY_EXISTS("030003", "Username already exists"),
    NOT_FOUND("030004", "%s not found"),
    INVALID_CREDENTIAL("030005", "Invalid credential"),;
    private String code;
    private String message;

    ResponseStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}
