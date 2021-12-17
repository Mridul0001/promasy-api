package com.promasy.api.users;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Document("users")
public class UserModel {
    @Id
    int userId;
    int adminId;
    boolean isAdmin;
    @NotNull
    String name;
    @NotNull
    String email;
    @NotNull
    String password;
    List<Projects> projects;
}
