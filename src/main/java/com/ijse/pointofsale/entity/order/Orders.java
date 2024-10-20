package com.ijse.pointofsale.entity.order;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ijse.pointofsale.entity.Customer;
import com.ijse.pointofsale.entity.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

@Entity
@Getter
@Setter
@Table(name = "orders")

public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Double totalAmount;
    
    private LocalDateTime orderDateTime;

    private String paymentMethod;

    @PrePersist 
    protected void onCreate() {
        if(this.orderDateTime == null) {
            this.orderDateTime = LocalDateTime.now();
        }
    }

    @ManyToOne
    @JsonIgnore 
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JsonIgnore 
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

}
