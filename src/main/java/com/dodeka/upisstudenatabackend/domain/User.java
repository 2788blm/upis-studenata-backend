package com.dodeka.upisstudenatabackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


    @Column(unique = true, nullable = false)
    @NotNull
    @Email
    private String email;


    @Column(unique = true, nullable = false)
    private String username;

    @Column
    @NotBlank(message = "password is mandatory")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;


    @Builder.Default
    private boolean active = true;


    @Transient
    private String accessToken;


    public boolean hasRole(String role) {
        return roles != null && roles.contains(role);
    }



    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null) return Collections.singleton(new SimpleGrantedAuthority("UNCONFIRMED"));
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }



    public static final String ROLE_STUDENT = "STUDENT";
    public static final String ROLE_ADMINISTRATOR_PREDMETA = "ADMINISTRATOR_PREDMETA";
    public static final String ROLE_ADMINISTRATOR_SISTEMA = "ADMINISTRATOR_SISTEMA";
    public static final String ROLE_SEKRETAR = "SEKRETAR";



}
