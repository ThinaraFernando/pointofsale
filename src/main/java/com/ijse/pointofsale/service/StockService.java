package com.ijse.pointofsale.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.pointofsale.dto.StockDto;
import com.ijse.pointofsale.entity.Stock;

@Service
public interface StockService {

    List<Stock> getAllStocks();

    Stock getStockById(Long id);

    StockDto addStock(StockDto stockDto); 

    Stock updateStock(Long id, Stock stock);

    void deleteStock(Long id);

    List<StockDto> getStockByItemId(Long itemId); 

}
