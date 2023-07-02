package com.project.ShowTime.Transformers;

import com.project.ShowTime.DTOs.ResponseDTOs.ResponseTicket;
import com.project.ShowTime.Models.Show;

public class TicketTransformer {
    public static ResponseTicket convertToDto(Show show) {
        ResponseTicket responseTicket = ResponseTicket.builder().showTime(show.getTime())
                .showDate(show.getDate())
                .movieName(show.getMovie().getMovieName())
                .theatreName(show.getTheatre().getTheatreName())
                .location(show.getTheatre().getLocation()).build();

        return responseTicket;
    }
}
