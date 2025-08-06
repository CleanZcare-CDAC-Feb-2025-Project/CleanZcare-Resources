package com.cleanzcare.service;

import java.util.List;
import com.cleanzcare.dto.ServiceDTO;

public interface ServiceService {
    ServiceDTO createService(ServiceDTO serviceDTO);
    List<ServiceDTO> getAllServices();
    ServiceDTO getServiceById(Long id);
    List<ServiceDTO> getServicesByCategoryId(Long categoryId);
    void deleteService(Long id);
}
