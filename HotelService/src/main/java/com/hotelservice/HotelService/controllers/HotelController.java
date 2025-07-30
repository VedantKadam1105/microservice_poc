package com.hotelservice.HotelService.controllers;

import com.hotelservice.HotelService.entities.Hotel;
import com.hotelservice.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @PostMapping
    public ResponseEntity<Hotel>createHotel(@RequestBody Hotel hotel){
        String id= UUID.randomUUID().toString();
        hotel.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel)) ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Hotel>getSingleHotel(@PathVariable String id){
        return ResponseEntity.ok(hotelService.getHotel(id));
    }
    @GetMapping
    public ResponseEntity<List<Hotel>>getHotels(){
        return ResponseEntity.ok(hotelService.getAll());
    }

}
