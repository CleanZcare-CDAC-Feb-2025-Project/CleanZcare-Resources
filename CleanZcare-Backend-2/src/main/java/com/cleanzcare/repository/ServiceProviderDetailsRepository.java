package com.cleanzcare.repository;

import com.cleanzcare.entities.ServiceProviderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceProviderDetailsRepository extends JpaRepository<ServiceProviderDetails, Long> {
    Optional<ServiceProviderDetails> findByUserId(Long userId);
}