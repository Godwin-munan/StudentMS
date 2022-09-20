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

    //GET ALL
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    //RANDOM NUMBER METHOD
    public String randomNum(){
        Random rnd = new Random();
        int number = rnd.nextInt(99999);
        String code = String.format("%06d", number);

        Optional<Student> existingStudent = studentRepository.findByRegCode(code);
        if (existingStudent.isPresent()){
            number = rnd.nextInt(99999);
            code = String.format("%06d", number);
        }

        return code;
    }

    //POST ALL
    public ResponseEntity<Student> addStudent(Student student) {
        String code = randomNum();

        student.setRegCode(code);
        Student newStudent=studentRepository.save(student);

        return ResponseEntity.ok().body(newStudent);
    }

    //DELETE BY ID
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    //UPDATE
    public ResponseEntity<Student> updateStudent(Student student) {

        if(student.getRegCode() == null){
            String code = randomNum();
            student.setRegCode(code);
        }

        Student updateStudent = studentRepository.save(student);
        return ResponseEntity.ok().body(updateStudent);
    }

    //GET BY ID
    public Optional<Student> getAllStudentById(long id) {
        return studentRepository.findById(id);
    }
}
