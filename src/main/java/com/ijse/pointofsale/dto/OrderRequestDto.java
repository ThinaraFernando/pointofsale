package com.ijse.pointofsale.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderRequestDto {
    private Long customerId;

    private Long userId;

    private String paymentMethod;
    
    private List<OrderItemDto> items; 
    
    
}
