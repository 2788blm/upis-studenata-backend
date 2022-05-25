package com.dodeka.upisstudenatabackend.dto;

import com.dodeka.upisstudenatabackend.domain.Role;
import com.dodeka.upisstudenatabackend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String email;

    private String username;

    private String password;

    private Role role;

    private boolean active;


    public static UserDto userToDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .active(user.isActive())
                .build();
    }

}
