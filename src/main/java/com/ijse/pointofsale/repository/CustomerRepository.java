package com.ijse.pointofsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijse.pointofsale.entity.Customer;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {


}
