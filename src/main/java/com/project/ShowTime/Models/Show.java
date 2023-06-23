package com.project.ShowTime.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalTime time;

    private Date date;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Movie movie;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Theatre theatre;

    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    @JsonIgnore
    List<ShowSeat> showSeatList = new ArrayList<>();

}
