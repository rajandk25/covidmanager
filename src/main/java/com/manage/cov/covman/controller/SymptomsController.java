package com.manage.cov.covman.controller;

import com.manage.cov.covman.entity.SymptomAnswers;
import com.manage.cov.covman.entity.SymptomQuestion;
import com.manage.cov.covman.services.SymptomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symptoms")
@CrossOrigin(origins = "http://localhost:4200")
public class SymptomsController {

    private SymptomsService symptomsService;

    @Autowired
    public SymptomsController(SymptomsService symptomsService) {
        this.symptomsService = symptomsService;
    }

    //get all questions i.e. symptoms
    @GetMapping(value = "/questions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SymptomQuestion>> questions() {
        return new ResponseEntity<>(symptomsService.getAllQuestions(), HttpStatus.OK);
    }

    //get today's checkIn
    @GetMapping(value = "/checkIn/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SymptomAnswers> todayCheckInForStudent(@PathVariable Long studentId) {
        return new ResponseEntity<>(symptomsService.getDailyCheckIn(studentId), HttpStatus.OK);
    }

    //add today's check In
    @PostMapping(value = "/checkIn", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SymptomAnswers> addDailyCheckIn(@RequestBody SymptomAnswers dailyCheckIn) {
        SymptomAnswers checkInCreated =  symptomsService.addDailyCheckIn(dailyCheckIn);

        if (checkInCreated != null) {
            return new ResponseEntity<>(checkInCreated, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
