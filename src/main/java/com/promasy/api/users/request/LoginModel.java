package com.promasy.api.users.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginModel {
    @NotNull
    String email;
    @NotNull
    String password;
}
