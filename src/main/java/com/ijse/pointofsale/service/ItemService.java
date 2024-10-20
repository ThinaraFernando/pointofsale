package com.ijse.pointofsale.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.pointofsale.dto.ItemDto;
import com.ijse.pointofsale.entity.Item;


@Service

public interface ItemService {

    List<Item> getAllItems();

    Item getItemById(Long itemId);

    Item createItem(ItemDto itemDto);

    Item updateItem(Long itemId, Item item);
    
    void deleteItem(Long itemId);
}
