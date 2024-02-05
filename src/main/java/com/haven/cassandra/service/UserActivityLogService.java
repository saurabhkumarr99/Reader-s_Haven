package com.haven.cassandra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haven.cassandra.dao.UserActivityLogRepository;
import com.haven.cassandra.model.UserActivityLog;

import java.util.List;
import java.util.Optional;

@Service
public class UserActivityLogService {

    @Autowired
    private UserActivityLogRepository userActivityLogRepository;

    // Create
    public UserActivityLog saveOrUpdateUserActivityLog(UserActivityLog userActivityLog) {
        return userActivityLogRepository.save(userActivityLog);
    }

    // Read
    public List<UserActivityLog> getAllUserActivityLogs() {
        return (List<UserActivityLog>) userActivityLogRepository.findAll();
    }

    public Optional<UserActivityLog> getUserActivityLogById(int id) {
        return userActivityLogRepository.findById(id);
    }

    // Update
    public UserActivityLog updateUserActivityLog(UserActivityLog userActivityLog) {
        if (userActivityLogRepository.existsById(userActivityLog.getId())) {
            return userActivityLogRepository.save(userActivityLog);
        }
        // Handle non-existing user activity log
        return null;
    }

    // Delete
    public void deleteUserActivityLogById(int id) {
        userActivityLogRepository.deleteById(id);
    }

    
}
