package com.sm.wellnow.activity.service.impl;

import com.sm.wellnow.activity.dto.ActivityRequest;
import com.sm.wellnow.activity.dto.ActivityResponse;
import com.sm.wellnow.activity.entity.Activity;
import com.sm.wellnow.auth.entity.User;
import com.sm.wellnow.activity.repository.ActivityRepository;
import com.sm.wellnow.auth.repository.UserRepository;
import com.sm.wellnow.activity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    @Override
    public ActivityResponse trackActivity(ActivityRequest activityRequest) {
        User user = userRepository.findById(activityRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("Invalid user: " + activityRequest.getUserId()));

        Activity activity = Activity.builder()
                .user(user)
                .type(activityRequest.getActivityType())
                .duration(activityRequest.getDuration())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .startTime(activityRequest.getStartTime())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .build();

        Activity savedActivity = activityRepository.save(activity);
        return mapToResponse(savedActivity);
    }



    private ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUser().getId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }
    @Override
    public List<ActivityResponse> getUserActivities(String userId) {
        List<Activity> activityList = activityRepository.findByUserId(userId);
        return activityList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
