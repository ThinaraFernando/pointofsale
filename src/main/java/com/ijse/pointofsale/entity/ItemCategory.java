package com.ijse.pointofsale.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter

public class ItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long categoryId;

    private String categoryName;

    
    @JsonIgnore

    @OneToMany(mappedBy = "itemCategory")
    private List<Item> items;
}
