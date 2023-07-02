package com.project.ShowTime.Controllers;

import com.project.ShowTime.DTOs.RequestDTOs.RequestUserDto;
import com.project.ShowTime.DTOs.ResponseDTOs.ResponseUser;
import com.project.ShowTime.Models.Ticket;
import com.project.ShowTime.Models.User;
import com.project.ShowTime.Services.UserService;
import com.project.ShowTime.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public String addUser(@RequestBody RequestUserDto userDto){
        try {
            return userService.addUser(userDto);
        }
        catch(Exception e){
            System.out.println(e.getMessage()+" at addUser");
            return "Bad Request/User with the given emailId or mobileNo is already registered.";
        }
    }

    @GetMapping("/getByEmailId/{emailId}")
    public ResponseEntity<ResponseUser> getUserByEmailId(@PathVariable String emailId){
        ResponseUser user = new ResponseUser();
        try{
            user = userService.getUserByEmailId(emailId);
            UserTransformer.setStatus(user,true);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            System.out.println(e.getMessage()+" at getUserByEmailId");
            UserTransformer.setStatus(user,false);
            return new ResponseEntity<>(user,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByMobileNo/{mobile}")
    public ResponseEntity<ResponseUser> getUserByMobile(@PathVariable String mobile){
        ResponseUser user = new ResponseUser();
        try{
            user = userService.getUserByMobile(mobile);
            UserTransformer.setStatus(user,true);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            System.out.println(e.getMessage()+" at getUserByMobile");
            UserTransformer.setStatus(user,false);
            return new ResponseEntity<>(user,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getOldestUser")
    public ResponseEntity<ResponseUser> getOldestUser(){
        ResponseUser user = new ResponseUser();
        try{
            user = userService.getOldestUser();
            UserTransformer.setStatus(user,true);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e.getMessage()+" at getOldestUser");
            UserTransformer.setStatus(user,false);
            return new ResponseEntity<>(user,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAgeLessThan")
    public List<User> getAgeLessThan(@RequestParam int age){
        List<User> userList = userService.getAgeLessThan(age);
        return userList;
    }

    @GetMapping("/getAgeGreaterThan")
    public List<User> getAgeGreaterThan(@RequestParam int age){
        List<User> userList = userService.getAgeGreaterThan(age);
        return userList;
    }

    @GetMapping("/getAll")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/getTicketList/{userId}")
    public ResponseEntity<List<Ticket>> getTicketList(@PathVariable int userId){
        try{
            return new ResponseEntity<>(userService.getTicketList(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
