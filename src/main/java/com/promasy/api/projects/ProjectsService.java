package com.promasy.api.projects;

import org.springframework.stereotype.Service;

@Service
public interface ProjectsService {
    ProjectsModel addProject(ProjectsModel projectsModel);
    String getProjectDescription(int projectId);
}
