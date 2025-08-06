package com.cleanzcare.service;

import java.util.List;

import com.cleanzcare.dto.ServiceCategoryDTO;
import com.cleanzcare.entities.ServiceCategory;

public interface ServiceCategoryService {
	 ServiceCategory addCategory(ServiceCategoryDTO dto);
	 List<ServiceCategoryDTO> getAllCategories();
	 ServiceCategoryDTO getCategoryById(Long id);
	 ServiceCategoryDTO updateCategory(Long id, ServiceCategoryDTO dto);
	 void deleteCategory(Long id);


}
