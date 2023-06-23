package com.project.ShowTime.Models;

import com.project.ShowTime.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ShowSeats")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private int price;

    private boolean isAvailable;

    private boolean isFoodIncluded;

    @ManyToOne
    @JoinColumn
    private Show show;
}
