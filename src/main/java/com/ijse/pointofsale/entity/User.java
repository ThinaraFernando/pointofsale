package com.ijse.pointofsale.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ijse.pointofsale.entity.order.Orders;

import jakarta.persistence.Column;
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

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String username;

    private String password;

    

   

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Orders> orders;
}
