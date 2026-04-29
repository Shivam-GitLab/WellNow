package com.sm.wellnow.auth.entity;
// Package where the User entity is stored

import com.fasterxml.jackson.annotation.JsonIgnore; // Prevents infinite recursion in JSON
import com.sm.wellnow.recommendation.entity.Recommendation;
import com.sm.wellnow.activity.entity.Activity;
import jakarta.persistence.*; // JPA annotations
import lombok.*; // Lombok for boilerplate reduction
import org.hibernate.annotations.CreationTimestamp; // Auto-set creation time
import org.hibernate.annotations.UpdateTimestamp; // Auto-update timestamp

import java.time.LocalDateTime; // Date-time handling
import java.util.ArrayList; // List implementation
import java.util.List; // List interface

@Getter // Generates getter methods
@Setter // Generates setter methods
@Entity // Marks this class as a database entity
@NoArgsConstructor // No-argument constructor
@AllArgsConstructor // All-argument constructor
@Builder // Enables builder pattern
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.UUID)
    // Auto-generate UUID for unique identification
    private String id;

    @Column(unique = true, nullable = false)
    private String email;
    // User email (should be unique in real-world apps)

    @Column(nullable = false)
    private String password;
    // User password (⚠️ should be encrypted using BCrypt)

    private String firstName;
    // User's first name

    private String lastName;
    // User's last name

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    // One user can have multiple activities
    // mappedBy: refers to 'user' field in Activity entity
    // cascade ALL: save/delete propagates to activities
    // orphanRemoval: removes activity if detached
    @JsonIgnore // Prevents infinite loop in API response
    private List<Activity> activities = new ArrayList<>();
    // List of user activities

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    // One user can have multiple recommendations
    @JsonIgnore // Prevent recursion
    private List<Recommendation> recommendations = new ArrayList<>();
    // List of recommendations given to user

    @CreationTimestamp
    // Automatically set when record is created
    @Column(updatable = false)
    // Cannot be updated later
    private LocalDateTime createdAt;
    // Account creation timestamp

    @UpdateTimestamp
    // Automatically updated on every change
    private LocalDateTime updatedAt;
    // Last updated timestamp
}