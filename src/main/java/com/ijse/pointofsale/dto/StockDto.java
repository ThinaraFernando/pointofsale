package com.ijse.pointofsale.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StockDto {

    private Long stockId;

    private Integer quantityAvailable; 

    private Long itemId; 
    
    private String itemName;

    
    
}
