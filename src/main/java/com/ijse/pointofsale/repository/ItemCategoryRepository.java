package com.ijse.pointofsale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.pointofsale.entity.ItemCategory;

@Repository

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

}
