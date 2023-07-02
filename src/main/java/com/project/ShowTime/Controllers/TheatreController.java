package com.project.ShowTime.Controllers;

import com.project.ShowTime.DTOs.RequestDTOs.TheatreEntryDto;
import com.project.ShowTime.DTOs.RequestDTOs.TheatreSeatsEntryDto;
import com.project.ShowTime.Models.Show;
import com.project.ShowTime.Models.Theatre;
import com.project.ShowTime.Models.TheatreSeat;
import com.project.ShowTime.Services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("theatre")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

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

    @GetMapping("/getAll")
    public List<Theatre> getAll(){
        return theatreService.getAll();
    }

    @GetMapping("/getShowList/{theatreId}")
    public ResponseEntity<List<Show>> getShowList(@PathVariable int theatreId){
        try{
            return new ResponseEntity<>(theatreService.getShowList(theatreId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getTheatreSeats/{theatreId}")
    public ResponseEntity<List<TheatreSeat>> getTheatreSeats(@PathVariable int theatreId){
        try{
            return new ResponseEntity<>(theatreService.getTheatreSeats(theatreId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
