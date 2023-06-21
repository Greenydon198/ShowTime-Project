package com.project.ShowTime.Transformers;

import com.project.ShowTime.DTOs.RequestDTOs.RequestUserDto;
import com.project.ShowTime.DTOs.ResponseDTOs.ResponseUser;
import com.project.ShowTime.Models.User;

public class UserTransformer {

    public static User convertDtoToEntity(RequestUserDto userDto){
        User user = User.builder().username(userDto.getUsername()).
                age(userDto.getAge()).
                gender(userDto.getGender()).
                emailId(userDto.getEmailId()).
                mobileNo(userDto.getMobileNo()).build();
        return user;
    }

    public static ResponseUser convertEntityToDto(User user){
        ResponseUser responseUser = ResponseUser.builder().username(user.getUsername()).
                age(user.getAge()).
                gender(user.getGender()).
                emailId(user.getEmailId()).
                mobileNo(user.getMobileNo()).build();

        return responseUser;
    }

    public static void setStatus(ResponseUser user,boolean flag){//true=success && false=failed
        if(flag){
            user.setStatusCode("200");
            user.setStatusMessage("SUCCESS");
        }
        else{
            user.setStatusCode("500");
            user.setStatusMessage("Not Found");
        }
    }
}
