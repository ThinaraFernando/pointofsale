package com.ijse.pointofsale.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer qty;

    private Double price;
    
         @JsonIgnore

    @ManyToMany(mappedBy = "items")
    private List<Stock> stocks;

     @ManyToOne
    @JoinColumn(name = "category_id",nullable = false) 
    private ItemCategory itemCategory;
}
