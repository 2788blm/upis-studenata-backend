package com.dodeka.upisstudenatabackend.controllers;

import com.dodeka.upisstudenatabackend.domain.User;
import com.dodeka.upisstudenatabackend.services.UserService;
import jakarta.validation.Valid;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserService userService;


    @PostMapping("/createUser")
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(user));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/updateUser/{email}")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/deactivateUser/{email}")
    public ResponseEntity<Object> deactivateUser(@PathVariable String email) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.deactivateUser(email));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(email));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    // logovanje

}
