package com.senai.cineapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class MovieRequest {
    private String title;
    private String description;
    private int duration;
    private LocalDate releaseDate;
    private String imageURL;
    private Double score;
    private List<String> genres;
    private Long director_id;
}
