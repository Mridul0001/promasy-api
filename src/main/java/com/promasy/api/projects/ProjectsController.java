package com.promasy.api.projects;

import com.promasy.api.constants.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ProjectsController {
    @Autowired ProjectsService projectsService;

    @PostMapping("/addproject")
    public ResponseEntity addProject(@RequestBody ProjectsModel projectsModel){
        try {

            return new ResponseEntity<ProjectsModel>(projectsService.addProject(projectsModel),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(GlobalConstants.COMMON_ERROR_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getprojects/{projectId}")
    public ResponseEntity getProjects(@RequestParam(name = "projectId") int projectId){
        try {
            return new ResponseEntity<String>(projectsService.getProjectDescription(projectId),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(GlobalConstants.COMMON_ERROR_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
