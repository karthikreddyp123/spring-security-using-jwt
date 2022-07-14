package com.example.springsecurityjwttutorial.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public String username;
    public String email;
    private String name;
    private String lastName;
    private Boolean active;
}
