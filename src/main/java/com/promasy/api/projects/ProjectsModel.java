package com.promasy.api.projects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@Document("projects")
public class ProjectsModel {
    @Id
    int projectId;
    @NotNull
    String title;
    String description;
    @NotNull
    int userId;
}
