package com.example.springsecurityjwttutorial.controllers;

import com.example.springsecurityjwttutorial.entity.User;
import com.example.springsecurityjwttutorial.models.UserDto;
import com.example.springsecurityjwttutorial.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/info")
    public UserDto getUserDetails() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.findByEmail(email).get();
        return UserDto.builder().username(user.getUserName()).name(user.getName()).email(user.getEmail()).lastName(user.getLastName()).active(user.getActive()).build();
    }
}
