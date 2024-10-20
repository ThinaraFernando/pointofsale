package com.ijse.pointofsale.entity.order;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ijse.pointofsale.entity.Item;

import jakarta.persistence.Entity;

@Entity
@Getter
@Setter
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;

    private Integer quantity;
    private Double totalPrice;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order; 

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item; 
}
