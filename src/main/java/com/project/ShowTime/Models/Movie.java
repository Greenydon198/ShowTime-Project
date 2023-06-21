package com.project.ShowTime.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ShowTime.Enums.Genre;
import com.project.ShowTime.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//primary key

    @Column(nullable = false)
    private String movieName;

    private double duration;

    private Date releaseDate;

    @Column(scale = 2)
    private double rating;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Show> showList = new ArrayList<>();
}
