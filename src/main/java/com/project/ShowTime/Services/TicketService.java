package com.project.ShowTime.Services;

import com.project.ShowTime.DTOs.RequestDTOs.RequestTicketDto;
import com.project.ShowTime.DTOs.ResponseDTOs.ResponseTicket;
import com.project.ShowTime.Exceptions.SeatsNotFound;
import com.project.ShowTime.Exceptions.ShowNotFound;
import com.project.ShowTime.Exceptions.UserNotFound;
import com.project.ShowTime.Models.*;
import com.project.ShowTime.Repositories.ShowRepository;
import com.project.ShowTime.Repositories.TicketRepository;
import com.project.ShowTime.Repositories.UserRepository;
import com.project.ShowTime.Transformers.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender emailSender;

    public ResponseTicket bookTicket(RequestTicketDto requestTicketDto) throws ShowNotFound, UserNotFound, SeatsNotFound {
        int showId = requestTicketDto.getShowId();
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new ShowNotFound("Cannot find the Show");
        }
        int userId = requestTicketDto.getUserId();
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFound("Cannot find the User");
        }
        Show show = showOptional.get();
        User user = userOptional.get();

        int price = validateAndCalcPrice(show,requestTicketDto.getRequestedSeats());
        if(price==-1){
            throw new SeatsNotFound("Seats are Not Available");
        }
        else{
            setSeatStatus(show,requestTicketDto.getRequestedSeats());
        }

        Ticket ticket = Ticket.builder().totalPrice(price)
                .show(show)
                .user(user)
                .bookedSeats(requestTicketDto.getRequestedSeats()).build();

        ticket = ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        userRepository.save(user);
        show.getTicketList().add(ticket);
        showRepository.save(show);

        ResponseTicket responseTicket = TicketTransformer.convertToDto(show);
        responseTicket.setBookedSeats(requestTicketDto.getRequestedSeats());
        responseTicket.setTotalPrice(price);

        SimpleMailMessage simpleMessageMail = new SimpleMailMessage();

        String body = "Hi "+user.getUsername()+" ! \n"+
                "You have successfully booked a ticket. Please find the following details "+
                "booked seat No's"  + responseTicket.getBookedSeats()
                +" movie Name: " + show.getMovie().getMovieName()
                +" show Date is "+show.getDate()+
                " And show time is "+show.getTime()+
                ".\n Enjoy the Show !!!";

        simpleMessageMail.setSubject("Ticket Confirmation Mail");
        simpleMessageMail.setFrom("");//Enter the mail that you gave in application properties
        simpleMessageMail.setText(body);
        simpleMessageMail.setTo(user.getEmailId());

        emailSender.send(simpleMessageMail);

        return responseTicket;
    }

    private int validateAndCalcPrice(Show show,List<String> requestedSeats) {
        int price = 0;
        int cnt = 0;
        for(ShowSeat seat:show.getShowSeatList()){
            String  seatNo = seat.getSeatNo();
            if(requestedSeats.contains(seatNo)){
                if(seat.isAvailable())
                    price += seat.getPrice();
                else
                    return -1;
                cnt++;
            }
        }
        if(cnt<requestedSeats.size())return -1;
        return price;
    }

    private void setSeatStatus (Show show,List<String> requestedSeats) {
        for(ShowSeat seat:show.getShowSeatList()){
            String  seatNo = seat.getSeatNo();
            if(requestedSeats.contains(seatNo)){
                seat.setAvailable(false);
            }
        }
    }

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }
}
