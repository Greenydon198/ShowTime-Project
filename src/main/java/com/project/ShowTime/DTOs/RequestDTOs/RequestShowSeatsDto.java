package com.project.ShowTime.DTOs.RequestDTOs;

import lombok.Data;

@Data
public class RequestShowSeatsDto {
    private int showId;
    private int classicSeatPrice;
    private int premiumSeatPrice;
}
