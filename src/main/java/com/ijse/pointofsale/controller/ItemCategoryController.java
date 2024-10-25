package com.ijse.pointofsale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.pointofsale.dto.ItemCategoryDto;
import com.ijse.pointofsale.entity.ItemCategory;
import com.ijse.pointofsale.service.ItemCategoryService;

@RestController
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @GetMapping("/itemcategory")
    public ResponseEntity<List<ItemCategoryDto>> getAllItemCategories() {
        List<ItemCategoryDto> categories = itemCategoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/itemcategory/{categoryId}")
    public ResponseEntity<?> getItemCategoryById(@PathVariable Long categoryId) {
        ItemCategory category = itemCategoryService.getItemCategoryById(categoryId);
        if (category == null) {
            return ResponseEntity.status(404).body("Item category not found");
        } else {
            return ResponseEntity.ok(category);
        }
    }

    @PostMapping("/itemcategory")
    public ResponseEntity<?> createItemCategory(@RequestBody ItemCategory category) {
        // Validate category name
        if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Category name cannot be null or empty");
        }
        
        try {
            ItemCategory createdCategory = itemCategoryService.createItemCategory(category);
            return ResponseEntity.status(201).body(createdCategory);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating item category: " + e.getMessage());
        }
    }

    @PutMapping("/itemcategory/{categoryId}")
    public ResponseEntity<?> updateItemCategory(@PathVariable Long categoryId, @RequestBody ItemCategory category) {
        // Validate category name
        if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Category name cannot be null or empty");
        }

        ItemCategory updatedCategory = itemCategoryService.updateItemCategory(categoryId, category);
        if (updatedCategory == null) {
            return ResponseEntity.status(404).body("Item category not found");
        } else {
            return ResponseEntity.ok(updatedCategory);
        }
    }

    @DeleteMapping("/itemcategory/{categoryId}")
    public ResponseEntity<?> deleteItemCategory(@PathVariable("categoryId") Long categoryId) {
        try {
            itemCategoryService.deleteItemCategory(categoryId);
            return ResponseEntity.ok("Item category deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Item category not found");
        }
    }
}
