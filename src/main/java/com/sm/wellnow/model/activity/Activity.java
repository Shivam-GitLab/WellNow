package com.sm.wellnow.model.activity;  // Package where this entity class resides

import com.fasterxml.jackson.annotation.JsonIgnore; // Prevents fields from being serialized in JSON response
import com.sm.wellnow.model.recommendation.Recommendation;
import com.sm.wellnow.model.user.User;
import jakarta.persistence.*; // JPA annotations for ORM mapping
import lombok.*; // Lombok to reduce boilerplate (getters, setters, etc.)
import org.hibernate.annotations.CreationTimestamp; // Auto-set creation timestamp
import org.hibernate.annotations.JdbcTypeCode; // Used to define custom SQL types like JSON
import org.hibernate.annotations.UpdateTimestamp; // Auto-update timestamp on update
import org.hibernate.type.SqlTypes; // SQL type constants (e.g., JSON)

import java.time.LocalDateTime; // Date-time handling
import java.util.ArrayList; // List implementation
import java.util.List; // List interface
import java.util.Map; // Map for storing key-value pairs (JSON structure)

@Getter // Lombok: Generates getter methods
@Setter // Lombok: Generates setter methods
@Entity // Marks this class as a JPA entity (table in DB)
@NoArgsConstructor // Lombok: Generates no-args constructor
@AllArgsConstructor // Lombok: Generates all-args constructor
@Builder // Lombok: Enables builder pattern for object creation
public class Activity {

    @Id // Marks this field as primary key
    @GeneratedValue(strategy = GenerationType.UUID) // Auto-generates UUID as ID
    private String id; // Unique identifier for Activity

    @ManyToOne(fetch = FetchType.LAZY)
    // Many activities can belong to one user (relationship)
    // LAZY: user data will be loaded only when needed
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_activity_user"))
    // Defines foreign key column in DB
    @JsonIgnore // Prevents infinite recursion in JSON response
    private User user; // Associated User entity

    @Enumerated(EnumType.STRING)
    // Stores enum as STRING in DB (better readability than ordinal)
    private ActivityType type; // Type of activity (e.g., RUNNING, WALKING)

    @JdbcTypeCode(SqlTypes.JSON)
    // Tells Hibernate to treat this field as JSON type
    @Column(columnDefinition = "json")
    // DB column type explicitly set to JSON (MySQL/Postgres)
    private Map<String, Object> additionalMetrics;
    // Flexible data storage (e.g., steps, heartRate, distance)

    private Integer duration; // Duration of activity (in minutes/seconds)

    private Integer caloriesBurned; // Calories burned during activity

    private LocalDateTime startTime; // Activity start time

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    // One activity can have multiple recommendations
    // mappedBy: refers to 'activity' field in Recommendation entity
    // cascade ALL: all operations propagate (save, delete)
    // orphanRemoval: removes child if not linked
    @JsonIgnore // Prevent recursion / unnecessary data in API
    private List<Recommendation> recommendations = new ArrayList<>();
    // List of related recommendations

    @CreationTimestamp
    // Automatically sets value when record is created
    @Column(updatable = false)
    // Prevents modification after creation
    private LocalDateTime createdAt; // Record creation timestamp

    @UpdateTimestamp
    // Automatically updates when entity is updated
    private LocalDateTime updatedAt; // Last update timestamp
}