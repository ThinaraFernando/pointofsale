package com.ijse.pointofsale.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.pointofsale.dto.ItemCategoryDto;
import com.ijse.pointofsale.entity.ItemCategory;
import com.ijse.pointofsale.repository.ItemCategoryRepository;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    public ItemCategoryServiceImpl(ItemCategoryRepository itemCategoryRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
    }

    @Override
    public List<ItemCategoryDto> getAllCategories() {
        List<ItemCategory> categories = itemCategoryRepository.findAll();

        // Convert entities to DTOs
        return categories.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Utility method to convert ItemCategory entity to ItemCategoryDto
    private ItemCategoryDto convertToDto(ItemCategory category) {
        return new ItemCategoryDto(category.getCategoryId(), category.getCategoryName());
    }

    @Override
    public ItemCategory getItemCategoryById(Long id) {
        return itemCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public ItemCategory createItemCategory(ItemCategory itemCategory) {
        // Validate category name
        validateCategoryName(itemCategory.getCategoryName());
        return itemCategoryRepository.save(itemCategory);
    }

    @Override
    public ItemCategory updateItemCategory(Long id, ItemCategory itemCategory) {
        if (itemCategoryRepository.existsById(id)) {
            // Validate category name
            validateCategoryName(itemCategory.getCategoryName());
            itemCategory.setCategoryId(id); // Ensure the correct ID is used for update
            return itemCategoryRepository.save(itemCategory);
        }
        return null;
    }

    @Override
    public void deleteItemCategory(Long id) {
        if (itemCategoryRepository.existsById(id)) {
            itemCategoryRepository.deleteById(id);
        }
    }

    // Method to validate category name
    private void validateCategoryName(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
    }
}
