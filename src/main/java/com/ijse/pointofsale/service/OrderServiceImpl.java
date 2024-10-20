package com.ijse.pointofsale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.pointofsale.entity.order.Orders;
import com.ijse.pointofsale.repository.OrderRepository;


@Service

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public Orders getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));    }
  }


