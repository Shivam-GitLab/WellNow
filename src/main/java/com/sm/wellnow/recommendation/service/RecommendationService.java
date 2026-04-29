package com.sm.wellnow.recommendation.service;

import com.sm.wellnow.recommendation.dto.RecommendationRequest;
import com.sm.wellnow.recommendation.entity.Recommendation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecommendationService {
    Recommendation generateRecommendation(RecommendationRequest request);

    List<Recommendation> getActivityRecommendation(String activityId);

    List<Recommendation> getUserRecommendation(String userId);
}
