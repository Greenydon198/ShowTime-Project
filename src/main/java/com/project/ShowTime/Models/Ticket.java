package com.project.ShowTime.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Tickets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    private int totalPrice;

    private List<String> bookedSeats = new ArrayList<>();

    @CreationTimestamp
    private Date bookedOn;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Show show;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;
}
