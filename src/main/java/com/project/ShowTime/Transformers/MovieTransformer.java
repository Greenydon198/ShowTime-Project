package com.project.ShowTime.Transformers;

import com.project.ShowTime.DTOs.RequestDTOs.RequestMovieDto;
import com.project.ShowTime.Models.Movie;

public class MovieTransformer {
    public static Movie convertDtoToEntity(RequestMovieDto requestMovieDto) {
        Movie movie = Movie.builder().movieName(requestMovieDto.getMovieName())
                .duration(requestMovieDto.getDuration())
                .genre(requestMovieDto.getGenre())
                .language(requestMovieDto.getLanguage())
                .releaseDate(requestMovieDto.getReleaseDate())
                .rating(requestMovieDto.getRating()).build();

        return movie;
    }
}
