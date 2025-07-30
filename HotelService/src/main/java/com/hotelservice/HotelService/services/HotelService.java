package com.hotelservice.HotelService.services;

import com.hotelservice.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel getHotel(String id);
}
