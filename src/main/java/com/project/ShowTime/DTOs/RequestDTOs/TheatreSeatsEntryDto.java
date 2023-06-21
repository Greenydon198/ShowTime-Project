package com.project.ShowTime.DTOs.RequestDTOs;

import lombok.Data;

@Data
public class TheatreSeatsEntryDto {
    private String location;
    private int noOfSeatsInRow;
    private int noOfClassicRows;
    private int noOfPremiumRows;
}
