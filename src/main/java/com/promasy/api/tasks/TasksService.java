package com.promasy.api.tasks;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TasksService {
    List<TasksModel> getAllTasksByProjectId(int projectId);
    TasksModel addTask(TasksModel tasksModel);
    TasksModel updateTask(TasksModel tasksModel);
    void deleteTask(int taskId);
}
