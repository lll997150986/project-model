package com.springboot.springbootjdbc.repository;

import com.springboot.springbootjdbc.domain.Mood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodRepository extends JpaRepository<Mood,String> {

}
