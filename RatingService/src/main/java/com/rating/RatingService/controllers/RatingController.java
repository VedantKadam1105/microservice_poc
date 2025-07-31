package com.rating.RatingService.controllers;

import com.rating.RatingService.entities.Rating;
import com.rating.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating(){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatings());
    }

    @GetMapping("/user/{userid}")
    public ResponseEntity<List<Rating>> getAllRatingByUserId(@PathVariable String userid){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserId(userid));
    }

    @GetMapping("/hotel/{hotelid}")
    public ResponseEntity<List<Rating>> getAllRatingByHotelId(@PathVariable String hotelid){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByHotelId(hotelid));
    }
}
