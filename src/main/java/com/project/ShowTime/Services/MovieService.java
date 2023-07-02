package com.project.ShowTime.Services;

import com.project.ShowTime.DTOs.RequestDTOs.RequestMovieDto;
import com.project.ShowTime.Exceptions.MovieNotFound;
import com.project.ShowTime.Models.Movie;
import com.project.ShowTime.Models.Show;
import com.project.ShowTime.Repositories.MovieRepository;
import com.project.ShowTime.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public void addMovie(RequestMovieDto requestMovieDto) {
        Movie movie = MovieTransformer.convertDtoToEntity(requestMovieDto);
        movieRepository.save(movie);
    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public List<Show> getShowList(int movieId) throws MovieNotFound {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if(movieOptional.isEmpty())
            throw new MovieNotFound("Cannot find the movie");

        return movieOptional.get().getShowList();
    }
}
