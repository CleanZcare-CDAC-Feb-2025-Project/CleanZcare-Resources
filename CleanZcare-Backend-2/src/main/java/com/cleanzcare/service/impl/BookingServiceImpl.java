package com.cleanzcare.service.impl;

import com.cleanzcare.dto.BookingRequestDTO;
import com.cleanzcare.dto.BookingResponseDTO;
import com.cleanzcare.entities.Booking;
import com.cleanzcare.repository.BookingRepository;
import com.cleanzcare.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO bookingDTO) {
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        Booking savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking, BookingResponseDTO.class);
    }
}
