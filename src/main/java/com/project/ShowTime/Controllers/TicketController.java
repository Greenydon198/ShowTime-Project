package com.project.ShowTime.Controllers;

import com.project.ShowTime.DTOs.RequestDTOs.RequestTicketDto;
import com.project.ShowTime.DTOs.ResponseDTOs.ResponseTicket;
import com.project.ShowTime.Models.Ticket;
import com.project.ShowTime.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/book-ticket")
    public ResponseEntity<ResponseTicket> bookTicket(@RequestBody RequestTicketDto requestTicketDto){
        ResponseTicket responseTicket = new ResponseTicket();
        try{
            responseTicket = ticketService.bookTicket(requestTicketDto);
            responseTicket.setStatusMessage("Ticket Booked Successfully.");
            return new ResponseEntity<>(responseTicket,HttpStatus.ACCEPTED);
        }catch(Exception e){
            responseTicket.setStatusMessage(e.getMessage());
            return new ResponseEntity<>(responseTicket,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public List<Ticket> getAll(){
        return ticketService.getAll();
    }

}
