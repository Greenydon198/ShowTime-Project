package com.project.ShowTime.DTOs.RequestDTOs;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class RequestShowDto {
    private LocalTime showStartTime;
    private Date showDate;
    private int movieId;
    private int theatreId;
}
