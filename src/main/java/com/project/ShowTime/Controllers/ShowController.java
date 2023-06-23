package com.project.ShowTime.Controllers;

import com.project.ShowTime.DTOs.RequestDTOs.RequestShowDto;
import com.project.ShowTime.DTOs.RequestDTOs.RequestShowSeatsDto;
import com.project.ShowTime.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    ShowService showService;

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
}
