package com.haven.cassandra.dao;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.haven.cassandra.model.BookRecommendation;

public interface BookRecommendationRepository extends CassandraRepository<BookRecommendation, Integer>{

}
