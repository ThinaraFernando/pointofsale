package com.ijse.pointofsale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.pointofsale.dto.ItemDto;
import com.ijse.pointofsale.entity.Item;
import com.ijse.pointofsale.entity.ItemCategory;
import com.ijse.pointofsale.repository.ItemCategoryRepository;
import com.ijse.pointofsale.repository.ItemRepository;

@Service


public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();   
     }

    @Override
    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).orElse(null);     }

   @Override
    public Item createItem(ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setQty(itemDto.getQty());
        item.setPrice(itemDto.getPrice());

        
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setCategoryId(itemDto.getItemCategory().getCategoryId());

        
        ItemCategory fetchedCategory = itemCategoryRepository.findById(itemCategory.getCategoryId()).orElse(null);
        if (fetchedCategory != null) {
            itemCategory.setCategoryName(fetchedCategory.getCategoryName()); 
            item.setItemCategory(fetchedCategory);
        }

        return itemRepository.save(item); 
    }
    @Override
    public Item updateItem(Long itemId, Item item) {
        Item existingItem = itemRepository.findById(itemId).orElse(null); 
        if (existingItem != null) {
            
            existingItem.setName(item.getName()); 
            existingItem.setPrice(item.getPrice()); 
            existingItem.setQty(item.getQty()); 
            
            return itemRepository.save(existingItem); 
        }
        return null; 
          }

    @Override
    public void deleteItem(Long itemId) {
        if (itemRepository.existsById(itemId)) {
            itemRepository.deleteById(itemId); 
        }
    }    
}


