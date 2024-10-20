package com.ijse.pointofsale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.pointofsale.dto.CustomerDto;
import com.ijse.pointofsale.entity.Customer;
import com.ijse.pointofsale.service.CustomerService;

@RestController
@CrossOrigin(origins ="*")

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")
     public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customer = customerService.getAllCustomers();
        return ResponseEntity.status(200).body(customer);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if(customer == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(customer);
        }    }

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        
         if (customer.getName() == null || customer.getName().isEmpty()) {
            return ResponseEntity.status(400).body("Please enter a valid customer name.");
        }

        
        if (customer.getContact() == null || customer.getContact().isEmpty()) {
            return ResponseEntity.status(400).body("Please enter a valid contact number.");
        }

        
        if (customer.getAddress() == null || customer.getAddress().isEmpty()) {
            return ResponseEntity.status(400).body("Please enter a valid address.");
        }

        try {
            Customer createdCustomer = customerService.createCustomer(customer);
            return ResponseEntity.status(201).body(createdCustomer);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("customer/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
        if(updatedCustomer== null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(updatedCustomer);
        }  
      }

   
}

