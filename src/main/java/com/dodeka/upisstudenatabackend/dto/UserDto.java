package com.dodeka.upisstudenatabackend.dto;

import com.dodeka.upisstudenatabackend.domain.User;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDto {

    private String email;

    private String username;

    private String password;

    private Set<String> roles;

    private boolean active;


    public static UserDto userToDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .active(user.isActive())
                .build();
    }

}
