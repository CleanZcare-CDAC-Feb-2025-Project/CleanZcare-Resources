package com.cleanzcare.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleanzcare.dto.ServiceCategoryDTO;
import com.cleanzcare.dto.ServiceDTO;
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
	    
	    @Override
	    public List<ServiceCategoryDTO> getAllCategories() {
	        return categoryRepository.findAll()
	            .stream()
	            .map(this::mapToCategoryDTO)  // Use the mapping method here
	            .collect(Collectors.toList());
	    }



	    @Override
	    public ServiceCategoryDTO getCategoryById(Long id) {
	        ServiceCategory category = categoryRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Category not found"));
	        
	        return mapToCategoryDTO(category);
	    }

        
	    @Override
	    public ServiceCategoryDTO updateCategory(Long id, ServiceCategoryDTO dto) {
	        ServiceCategory category = categoryRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Category not found"));

	        category.setCategoryName(dto.getCategoryName());
	        category.setDescription(dto.getDescription());

	        categoryRepository.save(category);

	        return new ServiceCategoryDTO(
	        	    category.getCategoryId(),
	        	    category.getCategoryName(),
	        	    category.getDescription(),
	        	    new ArrayList<>()  // or Collections.emptyList()
	        	);

	    }
	    
	    @Override
	    public void deleteCategory(Long id) {
	        if (!categoryRepository.existsById(id)) {
	            throw new RuntimeException("Category not found");
	        }
	        categoryRepository.deleteById(id);
	    }

	    private ServiceDTO mapToServiceDTO(Service service) {
	    	return new ServiceDTO(
	    		    service.getServiceId(),
	    		    service.getServiceName(),
	    		    service.getDescription(),
	    		    service.getPrice(),
	    		    service.getServiceCategory().getCategoryId()  // assuming proper mapping
	    		);

	    }

	    private ServiceCategoryDTO mapToCategoryDTO(ServiceCategory category) {
	        List<ServiceDTO> serviceDTOs = category.getServices().stream()
	            .map(this::mapToServiceDTO)
	            .collect(Collectors.toList());

	        return new ServiceCategoryDTO(
	            category.getCategoryId(),
	            category.getCategoryName(),
	            category.getDescription(),
	            serviceDTOs
	        );
	    }


}
