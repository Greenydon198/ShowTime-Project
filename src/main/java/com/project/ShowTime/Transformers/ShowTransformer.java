package com.project.ShowTime.Transformers;

import com.project.ShowTime.DTOs.RequestDTOs.RequestShowDto;
import com.project.ShowTime.Models.Show;
import lombok.Data;

@Data
public class ShowTransformer {
    public static Show convertDtoToEntity(RequestShowDto requestShowDto) {
        Show show = Show.builder().time(requestShowDto.getShowStartTime())
                .date(requestShowDto.getShowDate()).build();

        return show;
    }
}
