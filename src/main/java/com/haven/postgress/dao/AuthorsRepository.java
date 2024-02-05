package com.haven.postgress.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haven.postgress.model.Authors;

public interface AuthorsRepository extends JpaRepository<Authors, Integer>{

}
