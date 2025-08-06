package com.cleanzcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanzcare.entities.ServiceCategory;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer> {
	boolean existsByCategoryName(String categoryName);

}
