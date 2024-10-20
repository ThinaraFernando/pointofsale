package com.ijse.pointofsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijse.pointofsale.entity.order.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
