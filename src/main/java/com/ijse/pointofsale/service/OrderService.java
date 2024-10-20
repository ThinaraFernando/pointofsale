package com.ijse.pointofsale.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.pointofsale.entity.order.Orders;

@Service
public interface OrderService {

    List<Orders> getAllOrders();

    Orders createOrder(Orders order);

    Orders getOrderById(Long orderId);

}
