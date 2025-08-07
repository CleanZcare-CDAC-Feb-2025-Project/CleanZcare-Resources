package com.cleanzcare.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingResponseDTO {
    private Long id;
    private Long userId;
    private Long serviceId;

    @JsonFormat(pattern = "yyyy-MM-dd") // Ensures consistent JSON formatting
    private LocalDate date;

    private String time;
    private String status;
}
