package com.cleanzcare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleanzcare.dto.ServiceCategoryDTO;
import com.cleanzcare.entities.ServiceCategory;
import com.cleanzcare.repository.ServiceCategoryRepository;
import com.cleanzcare.service.ServiceCategoryService;

import jakarta.transaction.Transactional;


@Service
public class ServiceCategoryServiceImpl implements ServiceCategoryService  {
	 @Autowired
	    private ServiceCategoryRepository categoryRepository;

	    @Override
	    @Transactional
	    public ServiceCategory addCategory(ServiceCategoryDTO dto) {
	        if (categoryRepository.existsByCategoryName(dto.getCategoryName())) {
	            throw new RuntimeException("Category already exists");
	        }

	        ServiceCategory category = ServiceCategory.builder()
	                .categoryName(dto.getCategoryName())
	                .description(dto.getDescription())
	                .build();

	        return categoryRepository.save(category);
	    }

}
