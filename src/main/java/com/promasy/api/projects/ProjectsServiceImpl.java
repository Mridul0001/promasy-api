package com.promasy.api.projects;

import com.promasy.api.constants.GlobalConstants;
import com.promasy.api.helpers.SequenceService;
import com.promasy.api.repos.ProjectsRepository;
import com.promasy.api.repos.UserRepository;
import com.promasy.api.users.Projects;
import com.promasy.api.users.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectsServiceImpl implements ProjectsService{
    @Autowired UserRepository userRepository;
    @Autowired ProjectsRepository projectsRepository;
    @Autowired SequenceService sequenceService;

    @Override
    public ProjectsModel addProject(ProjectsModel projectsModel) {
        int projectId = sequenceService.getSequence(GlobalConstants.PROJECTS);
        projectsModel.setProjectId(projectId);
        UserModel userModel = userRepository.findUserById(projectsModel.getUserId());
        Projects projects = new Projects();
        projects.setProjectId(projectId);
        projects.setTitle(projectsModel.getTitle());
        userModel.getProjects().add(projects);
        userRepository.save(userModel);
        projectsRepository.save(projectsModel);
        return projectsModel;
    }

    @Override
    public String getProjectDescription(int projectId) {
        ProjectsModel projectsModel=projectsRepository.findProjectById(projectId);
        return projectsModel.getDescription();
    }
}
