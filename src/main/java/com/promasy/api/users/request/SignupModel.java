package com.promasy.api.users.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SignupModel {
    @NotNull
    int adminId;
    @NotNull
    int userId;
    @NotNull
    String name;
    @NotNull
    String password;
}
