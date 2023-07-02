package com.project.ShowTime.Controllers;

import com.project.ShowTime.DTOs.RequestDTOs.RequestShowDto;
import com.project.ShowTime.DTOs.RequestDTOs.RequestShowSeatsDto;
import com.project.ShowTime.Models.*;
import com.project.ShowTime.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/addShow")
    public String addShow(@RequestBody RequestShowDto requestShowDto){
        try{
            showService.addShow(requestShowDto);
            return "Show added Successfully";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/associate-seats")
    public String associateSeats(@RequestBody RequestShowSeatsDto requestShowSeatsDto){
        try{
            showService.associateSeats(requestShowSeatsDto);
            return "Show seats are paired up successfully.";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/HighestShowedMovie")//get the movie with the highest no. of shows
    public String highestShowedMovie(){
        try{
            return showService.highestShowedMovie();

        }catch(Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getAll")
    public List<Show> getAll(){
        return showService.getAll();
    }

    @GetMapping("/getShowSeats/{showId}")
    public ResponseEntity<List<ShowSeat>> getShowSeats(@PathVariable int showId){
        try{
            return new ResponseEntity<>(showService.getShowSeats(showId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getTicketList/{showId}")
    public ResponseEntity<List<Ticket>> getTicketList(@PathVariable int showId){
        try{
            return new ResponseEntity<>(showService.getTicketList(showId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
