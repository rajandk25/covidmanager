package com.manage.cov.covman.controller;


import com.manage.cov.covman.entity.ExposureIncident;
import com.manage.cov.covman.entity.Student;
import com.manage.cov.covman.entity.User;
import com.manage.cov.covman.services.EmailSender;
import com.manage.cov.covman.services.ExposureService;
import com.manage.cov.covman.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exposures")
@CrossOrigin(origins = "http://localhost:4200")
public class ExposureController {

    private ExposureService exposureService;

    private EmailSender emailSender;

    private StudentService studentService;

    @Autowired
    public ExposureController(ExposureService exposureService, EmailSender emailSender, StudentService studentService) {
        this.exposureService = exposureService;
        this.emailSender = emailSender;
        this.studentService = studentService;
    }

    //create a new exposure incident
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExposureIncident> addIncident(@RequestBody ExposureIncident exposureIncident) {
        ExposureIncident created = exposureService.addExposureIncident(exposureIncident);

        if (created != null) {
            sendEmail(created, "added");
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //get all the exposures for the students
    @PostMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExposureIncident>> exposuresForStudents(@RequestBody List<Long> studentIds) {
        List<ExposureIncident> exposureIncidents = exposureService.getExposuresForStudents(studentIds);

        return new ResponseEntity<>(exposureIncidents, HttpStatus.OK);
    }

    //update exposure
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExposureIncident> updateExposure(@RequestBody ExposureIncident exposureIncident){
        ExposureIncident updated = exposureService.addExposureIncident(exposureIncident);
        if (updated != null) {
            sendEmail(updated, "updated");
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    /**
     * Makes message and sends email
     * @param created
     * @param operation
     */
    private void sendEmail(ExposureIncident created, String operation) {
        Student student = created.getStudent();
        Student studentWithParent = studentService.getParent(student.getId());

        User parent = studentWithParent.getParent().getUser();

        String to = parent.getEmail();

        String subject = "Exposure has been " + operation + " for you child";

        String message = "Hi " + parent.getFirstName() + " " + parent.getLastName() + "\n\n";

        message += "Exposure Id: " + created.getId() + " has been " + operation + " for Student: " + student.getFirstName();

        message += "\n\nPlease login to CovManage for more details. \n\n. Thanks.";

        emailSender.sendMail(to, message, subject);
    }


}
