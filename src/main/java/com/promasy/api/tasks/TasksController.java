package com.promasy.api.tasks;

import com.promasy.api.constants.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class TasksController {
    @Autowired TasksService tasksService;

    @GetMapping("/tasks/{projectId}")
    public ResponseEntity getAllTasksByProjectId(@PathVariable(name = "projectId") int projectId){
        try {
            return new ResponseEntity<List<TasksModel>>(tasksService.getAllTasksByProjectId(projectId),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(GlobalConstants.COMMON_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addtask")
    public ResponseEntity addNewTask(@RequestBody TasksModel tasksModel){
        try {
            return new ResponseEntity<TasksModel>(tasksService.addTask(tasksModel),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(GlobalConstants.COMMON_ERROR_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatetask")
    public ResponseEntity updateTask(@RequestBody TasksModel tasksModel){
        try {
            return new ResponseEntity<TasksModel>(tasksService.updateTask(tasksModel),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(GlobalConstants.COMMON_ERROR_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletetask/{taskId}")
    public ResponseEntity deleteTask(@PathVariable(value = "taskId") int taskId){
        try {
            tasksService.deleteTask(taskId);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(GlobalConstants.COMMON_ERROR_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
