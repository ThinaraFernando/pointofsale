package com.ijse.pointofsale.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.pointofsale.dto.CustomerDto;
import com.ijse.pointofsale.entity.Customer;


@Service
public interface CustomerService {

public List<CustomerDto> getAllCustomers();

    Customer getCustomerById(Long customerId);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Long customerId, Customer customer);
    
    void deleteCustomer(Long customerId);

}
