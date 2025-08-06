package com.cleanzcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleanzcare.dto.ServiceCategoryDTO;
import com.cleanzcare.entities.ServiceCategory;
import com.cleanzcare.service.ServiceCategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/categories")
public class ServiceCategoryController {
	
	@Autowired
    private ServiceCategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody ServiceCategoryDTO dto) {
        ServiceCategory created = categoryService.addCategory(dto);
        return ResponseEntity.ok(created);
    }

}
