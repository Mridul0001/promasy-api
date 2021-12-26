package com.promasy.api.cbl;

import java.util.List;

public interface CblService {
    List<CblModel> getAllProjects();
    OrderModel placeOrder(OrderModel orderModel);
    OrderModel getOrderById(int orderId);
}
