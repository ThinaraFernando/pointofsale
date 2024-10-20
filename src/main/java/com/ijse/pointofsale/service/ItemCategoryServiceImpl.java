package com.ijse.pointofsale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.pointofsale.entity.ItemCategory;
import com.ijse.pointofsale.repository.ItemCategoryRepository;


@Service

public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryRepository  itemCategoryRepository;


    @Override
    public List<ItemCategory> getAllItemCategories() {
        return  itemCategoryRepository.findAll();   
     }

    @Override
    public ItemCategory getItemCategoryById(Long id) {
        return  itemCategoryRepository.findById(id).orElse(null); 
        }

        @Override
        public ItemCategory createItemCategory(ItemCategory itemCategory) {
            return  itemCategoryRepository.save(itemCategory);
        }
    
        @Override
        public ItemCategory updateItemCategory(Long id, ItemCategory itemCategory) {
            if ( itemCategoryRepository.existsById(id)) {
                itemCategory.setCategoryId(id); // Ensure the correct ID is used for update
                return  itemCategoryRepository.save(itemCategory);
            }
            return null;
        }
    
    @Override
    public void deleteItemCategory(Long id) {
        if ( itemCategoryRepository.existsById(id)) {
            itemCategoryRepository.deleteById(id); 
        }
        
    }

}


