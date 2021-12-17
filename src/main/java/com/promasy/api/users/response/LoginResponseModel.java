package com.promasy.api.users.response;

import com.promasy.api.users.Projects;
import com.promasy.api.users.Users;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponseModel {
    boolean isAdmin;
    String user;
    int userId;
}
