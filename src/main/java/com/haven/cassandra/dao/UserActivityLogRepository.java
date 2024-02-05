package com.haven.cassandra.dao;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.haven.cassandra.model.UserActivityLog;

public interface UserActivityLogRepository extends CassandraRepository<UserActivityLog, Integer>{

}
