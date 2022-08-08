package com.dodeka.upisstudenatabackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsersService usersService;
    private final JwtFilter jwtFilter;

    @Autowired
    public SpringSecurityConfig(UsersService usersService, JwtFilter jwtFilter) {
        this.usersService = usersService;
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.usersService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()   // authorize all users (both anonymous and logged in) to access the page starting with ‘/'
                .antMatchers("/users/login").permitAll()
                .antMatchers("/users/*").hasAuthority(User.ROLE_ADMINISTRATOR_SISTEMA)
                .antMatchers("/predmeti/*").hasAnyAuthority(User.ROLE_ADMINISTRATOR_PREDMETA, User.ROLE_ADMINISTRATOR_SISTEMA)
                .antMatchers("/izvestaji/*").hasAnyAuthority(User.ROLE_ADMINISTRATOR_PREDMETA, User.ROLE_ADMINISTRATOR_SISTEMA)
                .antMatchers("/skolskaGodina/*").hasAnyAuthority(User.ROLE_ADMINISTRATOR_PREDMETA, User.ROLE_ADMINISTRATOR_SISTEMA)
                .antMatchers("/upis/*").hasAuthority(User.ROLE_STUDENT)
                .antMatchers("/ankete/*").hasAnyAuthority(User.ROLE_SEKRETAR, User.ROLE_ADMINISTRATOR_SISTEMA)
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }



}

