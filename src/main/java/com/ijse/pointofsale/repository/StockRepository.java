package com.ijse.pointofsale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.pointofsale.entity.Stock;

@Repository


public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByItems_Id(Long itemId);

}
