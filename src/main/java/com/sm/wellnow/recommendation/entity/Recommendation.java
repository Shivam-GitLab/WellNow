package com.sm.wellnow.recommendation.entity;
// Package where this entity belongs

import com.fasterxml.jackson.annotation.JsonIgnore; // Prevents infinite recursion in JSON
import com.sm.wellnow.activity.entity.Activity;
import com.sm.wellnow.user.entity.User;
import jakarta.persistence.*; // JPA annotations for ORM mapping
import lombok.*; // Lombok to reduce boilerplate
import org.hibernate.annotations.CreationTimestamp; // Auto set created time
import org.hibernate.annotations.JdbcTypeCode; // Custom SQL type mapping (JSON)
import org.hibernate.annotations.UpdateTimestamp; // Auto update timestamp
import org.hibernate.type.SqlTypes; // SQL type constants

import java.time.LocalDateTime; // Date-time class
import java.util.List; // List collection

@Getter // Generates getters
@Setter // Generates setters
@Entity // Marks this class as a DB entity
@NoArgsConstructor // No-args constructor
@AllArgsConstructor // All-args constructor
@Builder // Builder pattern support
public class Recommendation {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.UUID)
    // Auto-generate UUID
    private String id; // Unique ID

    @ManyToOne(fetch = FetchType.LAZY)
    // Many recommendations can belong to one user
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_recommendation_user"))
    // Foreign key column
    @JsonIgnore // Avoid recursion in API response
    private User user; // Associated User

    @ManyToOne(fetch = FetchType.LAZY)
    // Many recommendations can belong to one activity
    @JoinColumn(name = "activity_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_recommendation_activity"))
    // Foreign key column
    @JsonIgnore // Avoid recursion
    private Activity activity; // Associated Activity

    private String type;
    // Type of recommendation (e.g., AI, SYSTEM, MANUAL)

    @Column(length = 2000)
    // Increase column size for long text
    private String recommendation;
    // Main recommendation message

    @JdbcTypeCode(SqlTypes.JSON)
    // Store as JSON in DB
    @Column(columnDefinition = "json")
    private List<String> improvements;
    // List of improvements (e.g., "Increase pace", "Improve posture")

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> suggestions;
    // Suggestions (e.g., "Try interval training")

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> safety;
    // Safety tips (e.g., "Stay hydrated", "Warm up properly")

    @CreationTimestamp
    // Auto set when record is created
    @Column(updatable = false)
    // Prevent updates
    private LocalDateTime createdAt;
    // Creation timestamp

    @UpdateTimestamp
    // Auto update on modification
    private LocalDateTime updatedAt;
    // Last updated timestamp
}