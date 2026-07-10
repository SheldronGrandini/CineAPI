package com.senai.cineapi.repository;

import com.senai.cineapi.model.Director;
import com.senai.cineapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
