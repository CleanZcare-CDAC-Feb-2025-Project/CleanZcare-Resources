package com.cleanzcare.service;

import com.cleanzcare.dto.BookingRequestDTO;
import com.cleanzcare.dto.BookingResponseDTO;

public interface BookingService {
    BookingResponseDTO createBooking(BookingRequestDTO bookingDTO);
}


