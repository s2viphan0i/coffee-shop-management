package com.csm.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterResponse {
    private String username;
    private String password;
    private String phone;
    private String address;
}
