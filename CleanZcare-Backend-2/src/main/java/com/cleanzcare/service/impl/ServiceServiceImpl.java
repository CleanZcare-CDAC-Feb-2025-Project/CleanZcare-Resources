package com.cleanzcare.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleanzcare.dto.ServiceDTO;
import com.cleanzcare.entities.ServiceCategory;
import com.cleanzcare.repository.ServiceCategoryRepository;
import com.cleanzcare.repository.ServiceRepository;
import com.cleanzcare.service.ServiceService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceCategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServiceDTO createService(ServiceDTO serviceDTO) {
        com.cleanzcare.entities.ServiceEntity service = modelMapper.map(serviceDTO, com.cleanzcare.entities.ServiceEntity.class);

        ServiceCategory category = categoryRepository.findById(serviceDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + serviceDTO.getCategoryId()));

        service.setServiceCategory(category);

        com.cleanzcare.entities.ServiceEntity saved = serviceRepository.save(service);
        return modelMapper.map(saved, ServiceDTO.class);
    }

    @Override
    public List<ServiceDTO> getAllServices() {
        List<com.cleanzcare.entities.ServiceEntity> services = serviceRepository.findAll();
        return services.stream()
                .map(service -> {
                    ServiceDTO dto = modelMapper.map(service, ServiceDTO.class);
                    dto.setCategoryId(service.getServiceCategory().getCategoryId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ServiceDTO getServiceById(Long id) {
        com.cleanzcare.entities.ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + id));
        ServiceDTO dto = modelMapper.map(service, ServiceDTO.class);
        dto.setCategoryId(service.getServiceCategory().getCategoryId());
        return dto;
    }

    @Override
    public List<ServiceDTO> getServicesByCategoryId(Long categoryId) {
        List<com.cleanzcare.entities.ServiceEntity> services = serviceRepository.findByServiceCategory_CategoryId(categoryId);
        return services.stream()
                .map(service -> {
                    ServiceDTO dto = modelMapper.map(service, ServiceDTO.class);
                    dto.setCategoryId(service.getServiceCategory().getCategoryId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
