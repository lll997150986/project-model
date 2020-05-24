package com.springboot.springbootjdbc.repository;

import com.springboot.springbootjdbc.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,String> {
}
