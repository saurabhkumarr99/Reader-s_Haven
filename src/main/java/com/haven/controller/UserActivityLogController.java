package com.haven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.haven.cassandra.model.UserActivityLog;
import com.haven.cassandra.service.UserActivityLogService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-activity-logs")
public class UserActivityLogController {

    @Autowired
    private UserActivityLogService userActivityLogService;

    // Create
    @PostMapping
    public ResponseEntity<UserActivityLog> createUserActivityLog(@RequestBody UserActivityLog userActivityLog) {
        UserActivityLog createdUserActivityLog = userActivityLogService.saveOrUpdateUserActivityLog(userActivityLog);
        return new ResponseEntity<>(createdUserActivityLog, HttpStatus.CREATED);
    }

    // Read
    @GetMapping
    public ResponseEntity<List<UserActivityLog>> getAllUserActivityLogs() {
        List<UserActivityLog> userActivityLogs = userActivityLogService.getAllUserActivityLogs();
        return new ResponseEntity<>(userActivityLogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserActivityLog> getUserActivityLogById(@PathVariable int id) {
        Optional<UserActivityLog> userActivityLog = userActivityLogService.getUserActivityLogById(id);
        return userActivityLog.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<UserActivityLog> updateUserActivityLog(@PathVariable int id, @RequestBody UserActivityLog updatedUserActivityLog) {
        updatedUserActivityLog.setId(id);
        UserActivityLog result = userActivityLogService.updateUserActivityLog(updatedUserActivityLog);
        return result != null
                ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserActivityLog(@PathVariable int id) {
        userActivityLogService.deleteUserActivityLogById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
