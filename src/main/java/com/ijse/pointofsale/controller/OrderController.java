package com.ijse.pointofsale.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.pointofsale.dto.OrderItemDto;
import com.ijse.pointofsale.dto.OrderRequestDto;
import com.ijse.pointofsale.entity.Customer;
import com.ijse.pointofsale.entity.Item;
import com.ijse.pointofsale.entity.User;
import com.ijse.pointofsale.entity.order.OrderDetail;
import com.ijse.pointofsale.entity.order.Orders;
import com.ijse.pointofsale.service.CustomerService;
import com.ijse.pointofsale.service.ItemService;
import com.ijse.pointofsale.service.OrderService;
import com.ijse.pointofsale.service.UserService;

@RestController
@CrossOrigin(origins ="*")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService; 

    @Autowired
    private CustomerService customerService;  
    @Autowired
    private UserService userService;  

    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = orderService.getAllOrders();

        return ResponseEntity.status(200).body(orders);
    }


    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        try {
            if (orderRequestDto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order request cannot be null");
            }
            if (orderRequestDto.getItems() == null || orderRequestDto.getItems().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order must contain at least one item");
            }
            if (orderRequestDto.getCustomerId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer ID cannot be null");
            }
            if (orderRequestDto.getUserId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID cannot be null");
            }
            if (orderRequestDto.getPaymentMethod() == null || orderRequestDto.getPaymentMethod().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment method cannot be null or empty");
            }

            Orders newOrder = createOrderFromDto(orderRequestDto);
            Orders savedOrder = orderService.createOrder(newOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private Orders createOrderFromDto(OrderRequestDto orderRequestDto) throws Exception {
        Orders order = new Orders();
        order.setTotalAmount(0.0);
        order.setOrderDateTime(LocalDateTime.now());
    
        // Fetch Customer and User from the IDs in the request
        Customer customer = customerService.getCustomerById(orderRequestDto.getCustomerId());
        if (customer == null) throw new Exception("Customer not found");
        order.setCustomer(customer);
    
        User user = userService.getUserById(orderRequestDto.getUserId());
        if (user == null) throw new Exception("User not found");
        order.setUser(user);
    
        // Set the payment method from the DTO
        order.setPaymentMethod(orderRequestDto.getPaymentMethod());
    
        List<OrderDetail> orderDetails = new ArrayList<>();
        double totalAmount = 0.0;
    
        // Loop through each item in the request and create OrderDetail
        for (OrderItemDto itemDto : orderRequestDto.getItems()) {
            Long itemId = itemDto.getItemId();
            Integer quantity = itemDto.getQuantity();
    
            if (itemId == null || quantity == null || quantity <= 0) {
                throw new Exception("Invalid item or quantity");
            }
    
            Item item = itemService.getItemById(itemId);
            if (item != null) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setItem(item);
                orderDetail.setQuantity(quantity);
                double itemTotal = item.getPrice() * quantity;
                orderDetail.setTotalPrice(itemTotal);
                orderDetail.setOrder(order); 
    
                orderDetails.add(orderDetail);
                totalAmount += itemTotal;
    
                // Update the stock quantity
                int currentQty = item.getQty();
                if (currentQty < quantity) {
                    throw new Exception("Not enough stock for item with ID " + itemId);
                }
                item.setQty(currentQty-quantity);
                itemService.updateItem(itemId, item); 
            } else {
                throw new Exception("Item with ID " + itemId + " not found");
            }
        }
    
        order.setOrderDetails(orderDetails);
        order.setTotalAmount(totalAmount);
    
        return order;
    }

   
}