package com.ijse.pointofsale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.pointofsale.dto.CustomerDto;
import com.ijse.pointofsale.entity.Customer;
import com.ijse.pointofsale.repository.CustomerRepository;

@Service

public class CustomerServiceImpl implements CustomerService {

     @Autowired
    private CustomerRepository customerRepository;


     @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        // Convert Customer entities to DTOs if necessary
        return customers.stream().map(customer -> convertToDto(customer)).collect(Collectors.toList());
    }

    // Conversion method
    private CustomerDto convertToDto(Customer customer) {
        return new CustomerDto(customer.getCustomerId(), customer.getName(), customer.getAddress(), customer.getContact());
    }
    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }    

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);    }

        @Override
        public Customer updateCustomer(Long customerId, Customer customer) {
            Customer existingCustomer = customerRepository.findById(customerId).orElse(null);
            if (existingCustomer != null) {
                existingCustomer.setName(customer.getName());
                existingCustomer.setContact(customer.getContact());
                existingCustomer.setAddress(customer.getAddress());
                return customerRepository.save(existingCustomer);   
            }
            return null; 
        }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    

}
