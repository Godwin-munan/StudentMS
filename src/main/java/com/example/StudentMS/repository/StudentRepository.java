package com.example.StudentMS.repository;

import com.example.StudentMS.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {


    Optional<Student> findByRegCode(String code);
}
