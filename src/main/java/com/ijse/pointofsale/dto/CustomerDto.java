package com.ijse.pointofsale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor 

public class CustomerDto {

    private Long customerId; 

    private String name; 

    private String contact; 

    private String address; 
}
