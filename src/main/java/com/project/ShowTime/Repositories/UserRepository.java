package com.project.ShowTime.Repositories;

import com.project.ShowTime.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "Select * from users where age <= :value",nativeQuery = true)
    List<User> usersLessThan(int value);

    @Query(value = "Select * from users where age >= :value",nativeQuery = true)
    List<User> usersGreaterThan(int value);

    User findByEmailId(String emailId);

    User findByMobileNo(String mobile);
}
