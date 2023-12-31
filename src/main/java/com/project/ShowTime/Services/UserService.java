package com.project.ShowTime.Services;

import com.project.ShowTime.DTOs.RequestDTOs.RequestUserDto;
import com.project.ShowTime.DTOs.ResponseDTOs.ResponseUser;
import com.project.ShowTime.Exceptions.UserNotFound;
import com.project.ShowTime.Models.Ticket;
import com.project.ShowTime.Models.User;
import com.project.ShowTime.Repositories.UserRepository;
import com.project.ShowTime.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    //add error if email already present
    public String addUser(RequestUserDto userDto){
        User user = UserTransformer.convertDtoToEntity(userDto);
        userRepository.save(user);
        return "User added Successfully";
    }

    public ResponseUser getOldestUser() throws UserNotFound {

        User oldestUser = null;
        int max_age = 0;
        List<User> userList = userRepository.findAll();

        for(User user:userList){
            if(max_age<user.getAge()){
                oldestUser = user;
                max_age = user.getAge();
            }
        }

        if(oldestUser==null)
            throw new UserNotFound("User Not found");

        ResponseUser responseUser = UserTransformer.convertEntityToDto(oldestUser);

        return responseUser;
    }

    public ResponseUser getUserByEmailId(String emailId) {
        User user = userRepository.findByEmailId(emailId);
        return UserTransformer.convertEntityToDto(user);
    }

    public ResponseUser getUserByMobile(String mobile) {
        User user = userRepository.findByMobileNo(mobile);
        return UserTransformer.convertEntityToDto(user);
    }

    public List<User> getAgeLessThan(int age) {
        return userRepository.usersLessThan(age);
    }

    public List<User> getAgeGreaterThan(int age) {
        return userRepository.usersGreaterThan(age);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<Ticket> getTicketList(int userId) throws UserNotFound {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty())
            throw new UserNotFound("Cannot find the User.");

        return userOptional.get().getTicketList();
    }
}
