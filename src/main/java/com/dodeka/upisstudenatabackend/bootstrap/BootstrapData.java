package com.dodeka.upisstudenatabackend.bootstrap;

import com.dodeka.upisstudenatabackend.security.User;
import com.dodeka.upisstudenatabackend.security.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class BootstrapData implements CommandLineRunner {


    private final UsersService usersService;

//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public BootstrapData(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public void run(String... args) throws Exception {
//        User user = new User();
//        user.setEmail("veljkostambolija@gmail.com");
//        user.setUsername("veljkostambolija");
//        user.setPassword(this.passwordEncoder.encode("password"));
//        user.setRoles(Collections.singleton(User.ROLE_ADMINISTRATOR_SISTEMA));
//        usersService.createUser(user);
    }
}
