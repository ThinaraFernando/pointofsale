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

import com.ijse.pointofsale.entity.ItemCategory;
import com.ijse.pointofsale.service.ItemCategoryService;


@RestController
@CrossOrigin(origins ="*")

public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @GetMapping("/itemcategory")
    public ResponseEntity<List<ItemCategory>> getAllItemCategories() {
        List<ItemCategory> categories = itemCategoryService.getAllItemCategories();
        return ResponseEntity.status(200).body(categories);
    }

    @GetMapping("/itemcategory/{categoryId}")
    public ResponseEntity<ItemCategory> getItemCategoryById(@PathVariable Long categoryId) {
        ItemCategory category = itemCategoryService.getItemCategoryById(categoryId);
        if (category == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(category);
        }
    }

    @PostMapping("/itemcategory")
    public ResponseEntity<?> createItemCategory(@RequestBody ItemCategory category) {
        try {
            ItemCategory createdCategory = itemCategoryService.createItemCategory(category);
            return ResponseEntity.status(201).body(createdCategory);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/itemcategory/{categoryId}")
    public ResponseEntity<ItemCategory> updateItemCategory(@PathVariable Long categoryId, @RequestBody ItemCategory category) {
        ItemCategory updatedCategory = itemCategoryService.updateItemCategory(categoryId, category);
        if (updatedCategory == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(updatedCategory);
        }
    }

     @DeleteMapping("/itemcategory/{categoryId}")
    public void deleteItemCategory(@PathVariable("categoryId") Long categoryId) {
        itemCategoryService.deleteItemCategory(categoryId);
    }

}
