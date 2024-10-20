package com.ijse.pointofsale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.pointofsale.dto.ItemDto;
import com.ijse.pointofsale.entity.Item;
import com.ijse.pointofsale.service.ItemService;


@RestController
@CrossOrigin(origins ="*")

public class ItemController {

  @Autowired
    private ItemService itemService;

    @GetMapping("/item")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> item = itemService.getAllItems();
        return ResponseEntity.status(200).body(item);
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable Long itemId) {
        Item item = itemService.getItemById(itemId);
        if (item == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(item);
        }
    }

     @PostMapping("/item")
    public ResponseEntity<?> createItem(@RequestBody ItemDto itemDto) { // Accept ItemDto
        try {
            Item createdItem = itemService.createItem(itemDto); // Pass ItemDto to service
            return ResponseEntity.status(201).body(createdItem);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @PutMapping("/item/{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable Long itemId, @RequestBody Item item) {
        Item updatedItem = itemService.updateItem(itemId, item);
        if (updatedItem == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(updatedItem);
        }
    }

    @DeleteMapping("/item/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
    }





    
}
