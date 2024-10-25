package com.ijse.pointofsale.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.pointofsale.dto.ItemCategoryDto;
import com.ijse.pointofsale.entity.ItemCategory;


@Service

public interface ItemCategoryService {

   List<ItemCategoryDto> getAllCategories();
   
    ItemCategory getItemCategoryById(Long id);

    ItemCategory createItemCategory(ItemCategory itemCategory);

    ItemCategory updateItemCategory(Long id, ItemCategory itemCategory);

     void deleteItemCategory(Long id);

}
