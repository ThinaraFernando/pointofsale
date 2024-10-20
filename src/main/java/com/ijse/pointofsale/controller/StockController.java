package com.ijse.pointofsale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.pointofsale.dto.StockDto;
import com.ijse.pointofsale.entity.Stock;
import com.ijse.pointofsale.service.StockService;


@RestController
@CrossOrigin(origins ="*")

@RequestMapping("/stock")
public class StockController {
 @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        return ResponseEntity.status(200).body(stocks);
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long stockId) {
        Stock stock = stockService.getStockById(stockId);
        if (stock == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(stock);
        }
    }

    @PostMapping
public ResponseEntity<?> addStock(@RequestBody StockDto stockDto) {
    try {
        StockDto createdStock = stockService.addStock(stockDto);
        return ResponseEntity.status(201).body(createdStock); 
    } catch (Exception e) {
        return ResponseEntity.status(400).body(null); 
    }
}

    @PutMapping("/{stockId}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long stockId, @RequestBody Stock stock) {
        Stock updatedStock = stockService.updateStock(stockId, stock);
        if (updatedStock == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(updatedStock);
        }
    }
}
