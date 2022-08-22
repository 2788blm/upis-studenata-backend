package com.dodeka.upisstudenatabackend.controllers;

import com.dodeka.upisstudenatabackend.security.*;
import jakarta.validation.Valid;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;

//    private AuthenticationManager authenticationManager;

//    private JwtUtil jwtUtil;

    @Autowired
    public UsersController(UsersService usersService/*, AuthenticationManager authenticationManager, JwtUtil jwtUtil*/) {
        this.usersService = usersService;
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/createUser")
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usersService.createUser(user));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usersService.updateUser(user));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/deactivateUser/{email}")
    public ResponseEntity<Object> deactivateUser(@PathVariable String email) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usersService.deactivateUser(email));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usersService.getUserByEmail(email));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/getAll")
    public List<User> listUsers() {
        return usersService.listUsers();
    }


//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
//                                                                                        loginRequest.getPassword()));
//        } catch(Exception ex) {
//            ex.printStackTrace();
//            return ResponseEntity.status(403).build();
//        }
//        return ResponseEntity.ok(new LoginResponse(jwtUtil.generateToken(loginRequest.getUsername())));
//    }


}
