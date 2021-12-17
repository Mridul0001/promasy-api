package com.promasy.api.tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@Document("tasks")
public class TasksModel {
    @Id
    int taskId;
    int projectId;
    @NotNull
    int userId;

    @NotNull
    String title;
    @NotNull
    String currStatus;
    @NotNull
    String assignedUser;
}
