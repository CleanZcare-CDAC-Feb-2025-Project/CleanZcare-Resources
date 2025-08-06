package com.cleanzcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    // GET all
    @GetMapping("/service-categories")
    public ResponseEntity<List<ServiceCategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // GET by ID
    @GetMapping("/service-categories/{id}")
    public ResponseEntity<ServiceCategoryDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    
    @PutMapping("/service-categories/{id}")
    public ResponseEntity<ServiceCategoryDTO> updateCategory(@PathVariable Long id, @RequestBody ServiceCategoryDTO dto) {
        return ResponseEntity.ok(categoryService.updateCategory(id, dto));
    }
    @DeleteMapping("/service-categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
    	categoryService.deleteCategory(id);
        return ResponseEntity.ok("Deleted successfully");
    }



}
