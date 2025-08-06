package com.cleanzcare.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCategoryDTO {
	private Long categoryId; 
	
	@NotBlank(message = "Category name is required")
    private String categoryName;

    private String description;

    private List<ServiceDTO> services;  // nested list of services
}
