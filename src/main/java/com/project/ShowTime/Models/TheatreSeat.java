package com.project.ShowTime.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ShowTime.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theatre_seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheatreSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;//1A, 26G, etc

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Theatre theatre;
}
