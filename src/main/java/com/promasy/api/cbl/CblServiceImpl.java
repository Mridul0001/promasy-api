package com.promasy.api.cbl;

import com.promasy.api.constants.GlobalConstants;
import com.promasy.api.helpers.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CblServiceImpl implements CblService{
    @Autowired CblRepository cblRepository;
    @Autowired OrderRepository orderRepository;
    @Autowired SequenceService sequenceService;
    @Override
    public List<CblModel> getAllProjects() {
        return cblRepository.findAll();
    }

    @Override
    public OrderModel placeOrder(OrderModel orderModel) {
        int id = sequenceService.getSequence(GlobalConstants.ORDERS);
        orderModel.setId(id);
        orderRepository.save(orderModel);
        return orderModel;
    }

    @Override
    public OrderModel getOrderById(int orderId) {
        return orderRepository.findOrderById(orderId);
    }
}
