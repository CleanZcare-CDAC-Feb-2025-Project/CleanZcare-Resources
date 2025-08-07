package com.cleanzcare.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequestDTO {
    private Long userId;
    private Long serviceId;

    @JsonFormat(pattern = "yyyy-MM-dd") // Matches your Postman input format
    private LocalDate date;

    private String time;
    private String status;
}


