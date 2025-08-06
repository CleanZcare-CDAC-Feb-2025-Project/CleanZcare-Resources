package com.cleanzcare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    private Long serviceId;
    private String serviceName;
    private String description;
    private Double price;
    private Long categoryId; // FK reference to service category
}
