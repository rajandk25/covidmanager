package com.manage.cov.covman.controller;

import com.manage.cov.covman.entity.Student;
import com.manage.cov.covman.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping ("/students")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> parent(@PathVariable Long id) {
        return new ResponseEntity<Student>(studentService.getParent(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student created = studentService.addStudent(student);

        if (created != null) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
