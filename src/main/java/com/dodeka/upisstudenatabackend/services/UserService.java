package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.domain.User;
import com.dodeka.upisstudenatabackend.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    @Transactional
    public User createUser(User user) throws Exception {
        if(userRepository.findById(user.getEmail()).isPresent()){
            throw new Exception("User with email = " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user) throws NotFoundException {
        Optional<User> userO = userRepository.findById(user.getEmail());
        if(!userO.isPresent()){
            throw new NotFoundException("User with email = " + user.getEmail() + " doesn't exists");
        }
        User updatedUser = userO.get();
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword()); // ovo nece moci
        updatedUser.setEmail(user.getEmail());
        updatedUser.setRoles(user.getRoles());
        updatedUser.setActive(user.isActive());
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


}
