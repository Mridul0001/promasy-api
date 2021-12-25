package com.promasy.api.cbl;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CblRepository extends MongoRepository<CblModel,String> {
}
