package com.ijse.pointofsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ijse.pointofsale.entity.order.OrderDetail;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
