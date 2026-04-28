package com.sm.wellnow.model.activity;
// Package where this enum is stored (same as Activity entity for easy access)

public enum ActivityType {
    RUNNING,        // Represents running activity (e.g., jogging, sprinting)
    CYCLING,        // Represents cycling activity (indoor/outdoor biking)
    SWIMMING,       // Represents swimming activity
    WALKING,        // Represents walking activity (casual or brisk)
    YOGA,           // Represents yoga or stretching exercises
    WEIGHTLIFTING,  // Represents gym/strength training activities
    HIKING,         // Represents hiking or trekking
    DANCING,        // Represents dance-based workouts (e.g., Zumba)
    OTHER           // Fallback type for any activity not listed above
}