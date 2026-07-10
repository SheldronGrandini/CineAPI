package com.senai.cineapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nationality;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL)
    @JsonBackReference // Evita o loop infinito ao serializar o Diretor
    private List<Movie> movies = new ArrayList<>();
}
