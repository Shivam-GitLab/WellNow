package com.sm.wellnow.activity.dto;

import com.sm.wellnow.activity.enums.ActivityType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActivityRequest {
    private String userId;
    private ActivityType activityType;
    private Map<String, Object> additionalMetrics;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;

}
