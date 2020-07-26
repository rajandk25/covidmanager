package com.manage.cov.covman.controller;

import com.manage.cov.covman.entity.Employee;
import com.manage.cov.covman.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping ("/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /*@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Parent> parent(@PathVariable Long id) {
        return new ResponseEntity<Parent>(parentService.getParent(id), HttpStatus.OK);
    }*/

    @GetMapping(value = "/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> teachers() {
        return new ResponseEntity<>(employeeService.getAllTeachers(), HttpStatus.OK);
    }

    /*@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Parent> addParent(@RequestBody Parent parent) {
        Parent created = parentService.addParent(parent);

        if (created != null) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
