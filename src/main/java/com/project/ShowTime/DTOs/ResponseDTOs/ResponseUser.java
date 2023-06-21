package com.project.ShowTime.DTOs.ResponseDTOs;

import com.project.ShowTime.Enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUser {
    private String username;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String emailId;

    private String mobileNo;

    private String statusCode;
    private String statusMessage;

}
