package com.ijse.pointofsale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.pointofsale.dto.StockDto;
import com.ijse.pointofsale.entity.Item;
import com.ijse.pointofsale.entity.Stock;
import com.ijse.pointofsale.repository.ItemRepository;
import com.ijse.pointofsale.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepository stockRepository;

     @Autowired
    private ItemRepository itemRepository; 

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }

    @Override
public StockDto addStock(StockDto stockDto) {
    // Fetch the item
    Item item = itemRepository.findById(stockDto.getItemId())
        .orElseThrow(() -> new RuntimeException("Item not found"));

    // Create and populate the stock entity
    Stock stock = new Stock();
    stock.setQuantityAvailable(stockDto.getQuantityAvailable());
    stock.setItems(List.of(item)); 

    Stock createdStock = stockRepository.save(stock);

    // Populate the DTO with the stock and item details
    StockDto responseDto = new StockDto();
    responseDto.setStockId(createdStock.getStockId());
    responseDto.setQuantityAvailable(createdStock.getQuantityAvailable()); // Use the correct field here
    responseDto.setItemId(item.getId());
    responseDto.setItemName(item.getName());

    return responseDto; 
}


@Override
public Stock updateStock(Long id, Stock updatedStock) {
    if (stockRepository.existsById(id)) {
        
        Stock existingStock = stockRepository.findById(id).orElse(null);
        
        if (existingStock != null) {
            existingStock.setQuantityAvailable(updatedStock.getQuantityAvailable());
            existingStock.setItems(updatedStock.getItems()); 
            
            return stockRepository.save(existingStock);
        }
    }
    return null;
}

    @Override
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    public List<StockDto> getStockByItemId(Long itemId) {
       
        List<Stock> stocks = stockRepository.findByItems_Id(itemId); 
        // Convert the list of Stock entities to StockDto
        return stocks.stream().map(stock -> {
            StockDto stockDto = new StockDto();
            stockDto.setStockId(stock.getStockId());
            stockDto.setItemId(stock.getItems().get(0).getId()); 
            stockDto.setItemName(stock.getItems().get(0).getName()); 
            stockDto.setQuantityAvailable(stock.getQuantityAvailable());
            return stockDto;
        }).collect(Collectors.toList());
    }
 }


