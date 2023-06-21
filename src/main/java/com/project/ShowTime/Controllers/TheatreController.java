package com.project.ShowTime.Controllers;

import com.project.ShowTime.DTOs.RequestDTOs.TheatreEntryDto;
import com.project.ShowTime.DTOs.RequestDTOs.TheatreSeatsEntryDto;
import com.project.ShowTime.Models.Theatre;
import com.project.ShowTime.Services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("theatre")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @PostMapping("/add")
    public String addTheatre(@RequestBody TheatreEntryDto theatreEntryDto){
        try{
            theatreService.addTheatre(theatreEntryDto);
            return "Theatre added successfully.";
        }
        catch(Exception e){
            return "Theatre with the given location is already present.";
        }
    }

    @PostMapping("/addTheatreSeats")
    public String addTheatreSeats(@RequestBody TheatreSeatsEntryDto theatreSeatsEntryDto){
        try{
            theatreService.addTheatreSeats(theatreSeatsEntryDto);
            return "Theatre_Seats are added successfully.";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
}
