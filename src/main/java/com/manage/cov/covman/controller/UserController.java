package com.manage.cov.covman.controller;

import com.manage.cov.covman.entity.User;
import com.manage.cov.covman.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{email}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> user(@PathVariable String email, @PathVariable String password) {
        User user = userService.getUser(email, password);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
