package com.hotelservice.HotelService.services.impl;

import com.hotelservice.HotelService.entities.Hotel;
import com.hotelservice.HotelService.exception.ResourceNotFoundException;
import com.hotelservice.HotelService.repositories.HotelRepository;
import com.hotelservice.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel with id"+id+" not found"));
    }
}
