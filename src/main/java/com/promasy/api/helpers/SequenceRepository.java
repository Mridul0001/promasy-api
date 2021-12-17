package com.promasy.api.helpers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends MongoRepository<SequenceModel,String> {
    @Query("{sequenceName:'?0'}")
    SequenceModel findBySequenceName(String sequence);
}
