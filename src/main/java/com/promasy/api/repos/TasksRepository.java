package com.promasy.api.repos;

import com.promasy.api.tasks.TasksModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends MongoRepository<TasksModel,Integer> {
    @Query("{projectId:?0}")
    List<TasksModel> findAllTasksByProjectId(Integer projectId);
}
