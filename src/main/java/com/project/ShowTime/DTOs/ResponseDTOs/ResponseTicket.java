package com.project.ShowTime.DTOs.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTicket {
    private LocalTime showTime;
    private Date showDate;
    private String movieName;
    private String theatreName;
    private String location;
    private List<String> bookedSeats;
    private int totalPrice;
    private String statusMessage;
}
