package com.project.ShowTime.Services;

import com.project.ShowTime.DTOs.RequestDTOs.RequestMovieDto;
import com.project.ShowTime.Models.Movie;
import com.project.ShowTime.Repositories.MovieRepository;
import com.project.ShowTime.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(RequestMovieDto requestMovieDto) {
        Movie movie = MovieTransformer.convertDtoToEntity(requestMovieDto);
        movieRepository.save(movie);
    }
}
