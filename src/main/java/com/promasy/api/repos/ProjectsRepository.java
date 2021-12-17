package com.promasy.api.repos;

import com.promasy.api.projects.ProjectsModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends MongoRepository<ProjectsModel,Integer> {
    @Query("{projectId:?0}")
    ProjectsModel findProjectById(Integer projectId);
}
