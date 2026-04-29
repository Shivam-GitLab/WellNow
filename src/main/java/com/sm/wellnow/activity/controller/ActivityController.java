package com.sm.wellnow.activity.controller;

import com.sm.wellnow.activity.dto.ActivityRequest;
import com.sm.wellnow.activity.dto.ActivityResponse;
import com.sm.wellnow.activity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest activityRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(activityService.trackActivity(activityRequest));

    }
    @GetMapping()
    public ResponseEntity<List<ActivityResponse>> getUserActivities(
            @RequestHeader(value = "X-User-ID") String userId
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(activityService.getUserActivities(userId));
    }
}
