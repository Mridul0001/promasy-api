package com.promasy.api.repos;

import com.promasy.api.users.Projects;
import com.promasy.api.users.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<UserModel,Integer> {
    @Query("{email:'?0'}")
    UserModel findUserByEmail(String email);

    @Query("{userId:?0}")
    UserModel findUserById(Integer userId);

    @Query("{adminId:?0}")
    List<UserModel> findAll(Integer adminId);

    @Query(value = "{userId:?0}", fields = "{'projects':1}")
    UserModel findAssociatedProjects(Integer userId);
}
