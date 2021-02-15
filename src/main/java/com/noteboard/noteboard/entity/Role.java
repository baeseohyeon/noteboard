package com.noteboard.noteboard.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    ADMIN("ROLE_ADMIN"),
    ACCOUNT("ROLE_ACCOUNT");

    private String value;
}
