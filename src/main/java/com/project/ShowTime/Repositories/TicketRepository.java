package com.project.ShowTime.Repositories;

import com.project.ShowTime.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
