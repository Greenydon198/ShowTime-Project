package com.project.ShowTime.Controllers;

import com.project.ShowTime.DTOs.RequestDTOs.RequestMovieDto;
import com.project.ShowTime.Models.Movie;
import com.project.ShowTime.Models.Show;
import com.project.ShowTime.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

     @Autowired
     private MovieService movieService;

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

    @GetMapping("/getAll")
    public List<Movie> getAll(){
         return movieService.getAll();
    }

    @GetMapping("/getShowList/{movieId}")
    public ResponseEntity<List<Show>> getShowList(@PathVariable int movieId){
         try{
             return new ResponseEntity<>(movieService.getShowList(movieId), HttpStatus.OK);
         }catch (Exception e){
             return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
         }
    }
}
