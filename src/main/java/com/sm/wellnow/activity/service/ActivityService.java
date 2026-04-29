package com.sm.wellnow.activity.service;

import com.sm.wellnow.activity.dto.ActivityRequest;
import com.sm.wellnow.activity.dto.ActivityResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {
    ActivityResponse trackActivity(ActivityRequest activityRequest);

    List<ActivityResponse> getUserActivities(String userId);
}
