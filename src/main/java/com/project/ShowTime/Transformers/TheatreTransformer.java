package com.project.ShowTime.Transformers;

import com.project.ShowTime.DTOs.RequestDTOs.TheatreEntryDto;
import com.project.ShowTime.Models.Theatre;

public class TheatreTransformer {

    public static Theatre convertDtoToEntity(TheatreEntryDto theatreEntryDto){
        Theatre theatre = Theatre.builder().theatreName(theatreEntryDto.getTheatreName()).
                            location(theatreEntryDto.getLocation()).build();

        return theatre;
    }
}
