package com.project.ShowTime.DTOs.RequestDTOs;

import com.project.ShowTime.Enums.Genre;
import com.project.ShowTime.Enums.Language;
import lombok.Data;

import java.util.Date;

@Data
public class RequestMovieDto {
    private String movieName;
    private int duration;
    private Date releaseDate;
    private double rating;
    private Genre genre;
    private Language language;
}
