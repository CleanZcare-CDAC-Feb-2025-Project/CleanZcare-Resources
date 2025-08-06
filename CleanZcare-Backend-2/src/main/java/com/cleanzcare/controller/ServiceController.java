package com.cleanzcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cleanzcare.dto.ServiceDTO;
import com.cleanzcare.service.ServiceService;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@Validated @RequestBody ServiceDTO serviceDTO) {
        return ResponseEntity.ok(serviceService.createService(serviceDTO));
    }

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        return ResponseEntity.ok(serviceService.getAllServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.getServiceById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ServiceDTO>> getServicesByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(serviceService.getServicesByCategoryId(categoryId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
