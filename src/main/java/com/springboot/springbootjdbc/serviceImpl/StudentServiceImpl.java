package com.springboot.springbootjdbc.serviceImpl;

import com.springboot.springbootjdbc.domain.Student;
import com.springboot.springbootjdbc.domain.User;
import com.springboot.springbootjdbc.repository.StudentRepository;
import com.springboot.springbootjdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-24 19:10
 **/
@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    StudentRepository studentRepository;
    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
