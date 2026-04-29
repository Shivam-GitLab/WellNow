package com.sm.wellnow.activity.repository;


import com.sm.wellnow.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {
    List<Activity> findByUserId(String userId);
}