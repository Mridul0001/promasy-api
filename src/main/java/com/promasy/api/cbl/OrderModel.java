package com.promasy.api.cbl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document("orders")
public class OrderModel {
    @Id
    int id;
    List<SingleOrderModel> order;
}
