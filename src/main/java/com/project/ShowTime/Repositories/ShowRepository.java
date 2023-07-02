package com.project.ShowTime.Repositories;

import com.project.ShowTime.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {

    @Query(value = "Select movie_Id from shows group by movie_id order by count(*) desc limit 1",nativeQuery = true)
    int getHighestShowedMovie();
}

