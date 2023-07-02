package com.project.ShowTime.DTOs.RequestDTOs;

import lombok.Data;

import java.util.List;

@Data
public class RequestTicketDto {

    private int showId;
    private int userId;
    private List<String> requestedSeats;
}
