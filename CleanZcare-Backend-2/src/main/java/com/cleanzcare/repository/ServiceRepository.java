package com.cleanzcare.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cleanzcare.entities.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findByServiceCategory_CategoryId(Long categoryId);
}
