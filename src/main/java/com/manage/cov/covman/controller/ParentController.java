package com.manage.cov.covman.controller;

import com.manage.cov.covman.entity.Parent;
import com.manage.cov.covman.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping ("/parents")
@CrossOrigin(origins = "http://localhost:4200")
public class ParentController {

    private ParentService parentService;

    @Autowired
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Parent> parent(@PathVariable Long id) {
        return new ResponseEntity<>(parentService.getParent(id), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Parent> parentByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(parentService.getParentByUserId(id), HttpStatus.OK);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Parent> addParent(@RequestBody Parent parent) {
        Parent created = parentService.addParent(parent);

        if (created != null) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
