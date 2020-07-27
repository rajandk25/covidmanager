package com.manage.cov.covman.controller;

import com.manage.cov.covman.entity.Employee;
import com.manage.cov.covman.entity.ExposureIncident;
import com.manage.cov.covman.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> teachers() {
        return new ResponseEntity<>(employeeService.getAllTeachers(), HttpStatus.OK);
    }

    //Find an employee but the Id of the User object it contains
    @GetMapping(value = "/user/{employeeUserId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> employeeById(@PathVariable Long employeeUserId) {
        return new ResponseEntity<>(employeeService.getEmployee(employeeUserId), HttpStatus.OK);
    }

    //create a new exposure incident
    @PostMapping(value = "/exposures", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExposureIncident> addIncident(@RequestBody ExposureIncident exposureIncident) {
        ExposureIncident created = employeeService.addExposureIncident(exposureIncident);

        if (created != null) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //get all the exposures for the students
    @PostMapping(value = "/exposures/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExposureIncident>> exposuresForStudents(@RequestBody List<Long> studentIds) {
        List<ExposureIncident> exposureIncidents = employeeService.getExposuresForStudents(studentIds);

        return new ResponseEntity<>(exposureIncidents, HttpStatus.OK);
    }
}
