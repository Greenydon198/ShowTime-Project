package com.project.ShowTime.DTOs.RequestDTOs;

import com.project.ShowTime.Enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class RequestUserDto {
    private String username;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String emailId;

    private String mobileNo;
}
