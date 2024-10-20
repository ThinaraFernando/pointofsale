package com.ijse.pointofsale.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ItemDto {
    private Long id; 
    private String name; 
    private Integer qty; 
    private Double price; 
    private ItemCategoryDto itemCategory;

}
