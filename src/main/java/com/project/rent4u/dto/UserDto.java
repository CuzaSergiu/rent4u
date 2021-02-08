package com.project.rent4u.dto;

import lombok.Data;

@Data
public class UserDto {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String password;
    private Boolean isAdmin;


}
