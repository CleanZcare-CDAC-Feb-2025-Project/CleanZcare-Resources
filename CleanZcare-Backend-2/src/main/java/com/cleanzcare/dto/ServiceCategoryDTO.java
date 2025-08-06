package com.cleanzcare.dto;

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
	@NotBlank(message = "Category name is required")
    private String categoryName;

    private String description;

}
