package com.promasy.api.tasks;

import com.promasy.api.constants.GlobalConstants;
import com.promasy.api.helpers.SequenceService;
import com.promasy.api.projects.ProjectsModel;
import com.promasy.api.repos.ProjectsRepository;
import com.promasy.api.repos.TasksRepository;
import com.promasy.api.repos.UserRepository;
import com.promasy.api.users.Projects;
import com.promasy.api.users.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksServiceImpl implements TasksService{
    @Autowired TasksRepository tasksRepository;
    @Autowired SequenceService sequenceService;
    @Autowired ProjectsRepository projectsRepository;
    @Autowired UserRepository userRepository;
    @Override
    public List<TasksModel> getAllTasksByProjectId(int projectId) {
        return tasksRepository.findAllTasksByProjectId(projectId);
    }

    @Override
    public TasksModel addTask(TasksModel tasksModel) {
        int taskId = sequenceService.getSequence(GlobalConstants.TASKS);
        tasksModel.setTaskId(taskId);
        tasksRepository.save(tasksModel);
        //We also need to associate this task project with the user
        addProjectToUser(tasksModel.getProjectId(), tasksModel.getUserId());
        return tasksModel;
    }

    @Override
    public TasksModel updateTask(TasksModel tasksModel) {
        //We also need to associate this task project with the user
        addProjectToUser(tasksModel.getProjectId(),tasksModel.getUserId());
        tasksRepository.save(tasksModel);
        return tasksModel;
    }

    @Override
    public void deleteTask(int taskId) {
        tasksRepository.deleteById(taskId);
    }

    private void addProjectToUser(int projectId, int userId){
        boolean found = false;
        ProjectsModel projectsModel = projectsRepository.findProjectById(projectId);
        Projects project = new Projects();
        project.setProjectId(projectId);
        project.setTitle(projectsModel.getTitle());
        UserModel userModel=userRepository.findUserById(userId);
        for(Projects projects:userModel.getProjects()){
            if((projects.getProjectId()==project.getProjectId())){
                found=true;
                break;
            }
        }

        if(!found){
            userModel.getProjects().add(project);
            userRepository.save(userModel);
        }
    }

}
