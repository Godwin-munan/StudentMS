package com.example.StudentMS.service;

import com.example.StudentMS.model.Student;
import com.example.StudentMS.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public ResponseEntity addStudent(Student student) {
        Random rnd = new Random();
        int number = rnd.nextInt(99999);
        String code = String.format("%06d", number);
        Optional<Student> existingStudent=studentRepository.findByRegCode(code);
        if (existingStudent.isPresent()){
            return ResponseEntity.ok().body("This student already Exist with regCode: " + code);
        }

        student.setRegCode(code);
        Student newStudent=studentRepository.save(student);
        return ResponseEntity.ok().body(newStudent);

    }

    public void deleteStudent(long id) {

        studentRepository.deleteById(id);
    }
}
