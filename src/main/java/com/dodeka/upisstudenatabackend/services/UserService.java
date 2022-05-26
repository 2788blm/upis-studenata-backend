package com.dodeka.upisstudenatabackend.services;

import com.dodeka.upisstudenatabackend.domain.User;
import com.dodeka.upisstudenatabackend.dto.UserDto;
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
    public UserDto createUser(UserDto userDto) throws Exception {
        if(userRepository.findById(userDto.getEmail()).isPresent()){
            throw new Exception("User with email = " + userDto.getEmail() + " already exists");
        }
        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .roles(userDto.getRoles())
                .active(true)
                .build();
        return UserDto.userToDto(userRepository.save(user));
    }

    @Transactional
    public UserDto updateUser(UserDto userDto) throws NotFoundException {
        Optional<User> user = userRepository.findById(userDto.getEmail());
        if(!user.isPresent()){
            throw new NotFoundException("User with email = " + userDto.getEmail() + " doesn't exists");
        }
        User updatedUser = user.get();
        updatedUser.setUsername(userDto.getUsername());
        updatedUser.setPassword(userDto.getPassword());
        updatedUser.setEmail(userDto.getEmail());
        updatedUser.setRoles(userDto.getRoles());
        updatedUser.setActive(userDto.isActive());
        return UserDto.userToDto(userRepository.save(updatedUser));
    }

    @Transactional
    public UserDto deactivateUser(String email) throws NotFoundException {
        Optional<User> user = userRepository.findById(email);
        if(!user.isPresent()){
            throw new NotFoundException("User with email = " + email + " doesn't exists");
        }
        User updatedUser = user.get();
        updatedUser.setActive(false);
        return UserDto.userToDto(userRepository.save(updatedUser));
    }

    public UserDto getUserByEmail(String email) throws NotFoundException {
        Optional<User> userO = userRepository.findById(email);
        if(!userO.isPresent()){
            throw new NotFoundException("User with email = " + email + " doesn't exists");
        }
        return UserDto.userToDto(userO.get());
    }


}
