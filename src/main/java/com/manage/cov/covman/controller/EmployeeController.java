package com.manage.cov.covman.controller;

import com.manage.cov.covman.entity.Employee;
import com.manage.cov.covman.entity.User;
import com.manage.cov.covman.services.EmailSender;
import com.manage.cov.covman.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;


@RestController
@RequestMapping ("/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private EmployeeService employeeService;

    private EmailSender emailSender;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmailSender emailSender) {
        this.employeeService = employeeService;
        this.emailSender = emailSender;
    }

    @GetMapping(value = "/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> teachers() {
        return new ResponseEntity<>(employeeService.getAllTeachers(), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> employees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    //Find an employee but the Id of the User object it contains
    @GetMapping(value = "/user/{employeeUserId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> employeeById(@PathVariable Long employeeUserId) {
        return new ResponseEntity<>(employeeService.getEmployee(employeeUserId), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        User user = employee.getUser();
        Long pass = new Random().nextLong();
        user.setPassword(pass.toString());

        Employee created = employeeService.modifyEmployee(employee);

        if(created != null) {
            String subject = "Employee Account created";
            String message = "Your account has been created with your email id: " + user.getEmail() +
                    " and password:" +  pass + ". \n You can now login with these details.";
            String toEmail = created.getUser().getEmail();

            emailSender.sendMail(toEmail, message, subject);

            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updated = employeeService.modifyEmployee(employee);

        if(updated != null) {
            String subject = "Employee Account updated";
            String message = "Your account has been updated. Please login to check details.";
            String toEmail = updated.getUser().getEmail();

            emailSender.sendMail(toEmail, message, subject);

            return new ResponseEntity<>(updated, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }

        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }


}
