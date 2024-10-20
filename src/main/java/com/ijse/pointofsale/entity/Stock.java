package com.ijse.pointofsale.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    private Integer quantityAvailable;

    @ManyToMany
    @JoinTable(
        name = "item_stock",
        joinColumns = @JoinColumn(name = "stock_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )

        private List<Item> items;
}
