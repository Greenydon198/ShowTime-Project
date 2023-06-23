package com.project.ShowTime.Services;

import com.project.ShowTime.DTOs.RequestDTOs.RequestShowDto;
import com.project.ShowTime.DTOs.RequestDTOs.RequestShowSeatsDto;
import com.project.ShowTime.Enums.SeatType;
import com.project.ShowTime.Exceptions.MovieNotFound;
import com.project.ShowTime.Exceptions.ShowNotFound;
import com.project.ShowTime.Exceptions.TheatreNotFound;
import com.project.ShowTime.Models.*;
import com.project.ShowTime.Repositories.MovieRepository;
import com.project.ShowTime.Repositories.ShowRepository;
import com.project.ShowTime.Repositories.TheatreRepository;
import com.project.ShowTime.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheatreRepository theatreRepository;

    public void addShow(RequestShowDto requestShowDto) throws MovieNotFound,TheatreNotFound{
        Optional<Movie> movieOptional = movieRepository.findById(requestShowDto.getMovieId());
        if(movieOptional.isEmpty()){
            throw new MovieNotFound("Cannot find the Movie");
        }

        Optional<Theatre> theatreOptional = theatreRepository.findById(requestShowDto.getTheatreId());
        if(theatreOptional.isEmpty()){
            throw new TheatreNotFound("Cannot find the Theatre");
        }
        Movie movie = movieOptional.get();
        Theatre theatre = theatreOptional.get();

        Show show = ShowTransformer.convertDtoToEntity(requestShowDto);
        show.setMovie(movie);
        show.setTheatre(theatre);

        show = showRepository.save(show);

        movie.getShowList().add(show);
        theatre.getShowList().add(show);

        movieRepository.save(movie);
        theatreRepository.save(theatre);
    }

    public void associateSeats(RequestShowSeatsDto requestShowSeatsDto) throws ShowNotFound,TheatreNotFound{

        Optional<Show> showOptional = showRepository.findById(requestShowSeatsDto.getShowId());
        if(showOptional.isEmpty()){
            throw new ShowNotFound("Cannot find the Show.");
        }

        Show show = showOptional.get();
        Theatre theatre = show.getTheatre();

        if(theatre==null){
            throw new TheatreNotFound("Cannot find the Theatre.");
        }
        List<TheatreSeat> theatreSeats = theatre.getTheatreSeatList();
        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(TheatreSeat seat:theatreSeats){
            ShowSeat showSeat = ShowSeat.builder().seatNo(seat.getSeatNo())
                    .seatType(seat.getSeatType())
                    .show(show)
                    .isAvailable(true)
                    .isFoodIncluded(false).build();

            if(showSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(requestShowSeatsDto.getClassicSeatPrice());
            }
            else{
                showSeat.setPrice(requestShowSeatsDto.getPremiumSeatPrice());
            }
            showSeatList.add(showSeat);
        }
        showRepository.save(show);
    }
}
