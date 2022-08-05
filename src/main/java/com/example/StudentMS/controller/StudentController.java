package com.example.StudentMS.controller;

import com.example.StudentMS.model.Student;
import com.example.StudentMS.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/getAll")
    public List<Student> getStudents(){
        return studentService.getAllStudent();
    }

    @DeleteMapping(value = "/studentById/{id}")
    public void deleteStudents(@PathVariable("id") long id){

        studentService.deleteStudent(id);
    }

    @PostMapping(value ="/add" )
    public ResponseEntity addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
}
