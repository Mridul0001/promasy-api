package com.promasy.api.cbl;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderModel, Integer> {
    @Query("{id:?0}")
    OrderModel findOrderById(Integer id);
}
