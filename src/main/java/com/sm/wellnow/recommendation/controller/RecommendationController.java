package com.sm.wellnow.recommendation.controller;

import com.sm.wellnow.recommendation.dto.RecommendationRequest;
import com.sm.wellnow.recommendation.entity.Recommendation;
import com.sm.wellnow.recommendation.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendation(
            @RequestBody RecommendationRequest request) {
        Recommendation recommendation = recommendationService.generateRecommendation(request);
        return ResponseEntity.ok(recommendation);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(
            @PathVariable String userId
    ) {
        List<Recommendation> recommendationList
                = recommendationService.getUserRecommendation(userId);
        return ResponseEntity.ok(recommendationList);
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<Recommendation>> getActivityRecommendation(
            @PathVariable String activityId
    ) {
        List<Recommendation> recommendationList
                = recommendationService.getActivityRecommendation(activityId);
        return ResponseEntity.ok(recommendationList);
    }
}
