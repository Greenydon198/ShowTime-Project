package com.project.ShowTime.DTOs.RequestDTOs;

import com.project.ShowTime.Enums.Gender;
import lombok.Data;

@Data
public class RequestUserDto {
    private String username;
    private int age;
    private Gender gender;
    private String emailId;
    private String mobileNo;
}
