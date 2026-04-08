package com.nebula1.SpringAiCode.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.nebula1.SpringAiCode.servise.ReviewService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://loca lhost:5173") // 🔥 IMPORTANT for React
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public List<String> getReviews(@RequestParam String business,
                                  @RequestParam String serviceType) {
        return reviewService.generateReviews(business, serviceType);
    }
    //commit
}