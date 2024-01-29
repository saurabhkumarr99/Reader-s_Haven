package com.haven.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haven.model.Authors;

public interface AuthorsRepository extends JpaRepository<Authors, Integer>{

}
