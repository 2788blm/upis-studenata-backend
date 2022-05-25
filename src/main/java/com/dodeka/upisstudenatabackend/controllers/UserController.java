package com.dodeka.upisstudenatabackend.controllers;

import com.dodeka.upisstudenatabackend.dto.UserDto;
import com.dodeka.upisstudenatabackend.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/createUser")
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(userDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/updateUser/{email}")
    public ResponseEntity<Object> updateUser(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userDto));
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
