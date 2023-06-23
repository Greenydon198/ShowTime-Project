package com.project.ShowTime.Controllers;

import com.project.ShowTime.DTOs.RequestDTOs.RequestMovieDto;
import com.project.ShowTime.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

     @Autowired
     MovieService movieService;

     @PostMapping("/addMovie")
     public String addMovie(@RequestBody RequestMovieDto requestMovieDto){
         try{
             movieService.addMovie(requestMovieDto);
             return "Movie added Successfully";
         }
         catch(Exception e){
             System.out.println(e.getMessage()+" at addMovie");
             return e.getMessage();
         }
     }
}
