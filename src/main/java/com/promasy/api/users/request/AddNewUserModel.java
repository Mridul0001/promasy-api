package com.promasy.api.users.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddNewUserModel {
    @NotNull
    String email;
    @NotNull
    int adminId;
}
