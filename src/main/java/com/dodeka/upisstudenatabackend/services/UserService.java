package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.domain.User;
import com.dodeka.upisstudenatabackend.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    protected final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @Transactional
    public User createUser(User user) throws Exception {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new Exception("User with email = " + user.getEmail() + " already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (CollectionUtils.isEmpty(user.getRoles())) {
            throw new RuntimeException("Roles cannot be empty");
        }
        if (user.getRoles().contains(User.ROLE_STUDENT) && (user.getRoles().contains(User.ROLE_SEKRETAR) || user.getRoles().contains(User.ROLE_ADMINISTRATOR_PREDMETA) || user.getRoles().contains(User.ROLE_ADMINISTRATOR_SISTEMA))) {
            throw new RuntimeException("Student role cannot be assigned together with Sekretar, Administrator predmeta or Administrator sistema role.");
        }

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user) throws NotFoundException {
        if (StringUtils.hasText(user.getUsername())) {
            throw new RuntimeException("Name cannot be empty");
        }
        if (CollectionUtils.isEmpty(user.getRoles())) {
            throw new RuntimeException("Roles cannot be empty");
        }
        if (StringUtils.hasText(user.getEmail())) {
            throw new RuntimeException("Email cannot be empty");
        }
        if (user.getRoles().contains(User.ROLE_STUDENT) && (user.getRoles().contains(User.ROLE_SEKRETAR) || user.getRoles().contains(User.ROLE_ADMINISTRATOR_PREDMETA) || user.getRoles().contains(User.ROLE_ADMINISTRATOR_SISTEMA))) {
            throw new RuntimeException("Student role cannot be assigned together with Sekretar, Administrator predmeta or Administrator sistema role.");
        }
        Optional<User> userO = userRepository.findByUsername(user.getUsername());
        if(!userO.isPresent()){
            throw new NotFoundException("User with username = " + user.getUsername() + " doesn't exists");
        }
        User updatedUser = userO.get();
        updatedUser.setUsername(user.getUsername());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setRoles(user.getRoles());
        updatedUser.setActive(user.isActive());
        if (!StringUtils.hasText(user.getPassword())) {
            updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(updatedUser);
    }

    @Transactional
    public User deactivateUser(String email) throws NotFoundException {
        Optional<User> userO = userRepository.findById(email);
        if(!userO.isPresent()){
            throw new NotFoundException("User with email = " + email + " doesn't exists");
        }
        User updatedUser = userO.get();
        updatedUser.setActive(false);
            return userRepository.save(updatedUser);
    }

    public User getUserByEmail(String email) throws NotFoundException {
        Optional<User> userO = userRepository.findById(email);
        if(!userO.isPresent()){
            throw new NotFoundException("User with email = " + email + " doesn't exists");
        }
        return userO.get();
    }


    public List<User> listUsers() {
        List<User> usersToList = new ArrayList<>(userRepository.findAll());
        return usersToList;
    }
}
