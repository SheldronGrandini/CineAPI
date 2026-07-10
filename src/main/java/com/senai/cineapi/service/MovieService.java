package com.senai.cineapi.service;

import com.senai.cineapi.dto.MovieRequest;
import com.senai.cineapi.exception.FieldValidationException;
import com.senai.cineapi.exception.ResourceNotFoundException;
import com.senai.cineapi.model.Director;
import com.senai.cineapi.model.Movie;
import com.senai.cineapi.repository.MovieRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final DirectorService directorService;

    public MovieService(MovieRepository movieRepository, DirectorService directorService) {
        this.movieRepository = movieRepository;
        this.directorService = directorService;
    }

    //Faz a validação do filme e retorna o diretor
    private Director validateAndGetDirector(MovieRequest request) {
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new FieldValidationException("O título do filme é obrigatório.");
        }
        if (request.getScore() != null && (request.getScore() < 0.0 || request.getScore() > 10.0)) {
            throw new FieldValidationException("O score do filme deve estar entre 0.0 e 10.0.");
        }
        if (request.getDirector_id() == null) {
            throw new FieldValidationException("O ID do diretor é obrigatório.");
        }
        // Retorna o diretor
        return directorService.findById(request.getDirector_id());
    }

    //Faz a atribuição de dados do request para o objeto
    private void transferData(Movie movie, MovieRequest request, Director director) {
        movie.setTitle(request.getTitle().trim());
        movie.setDescription(request.getDescription());
        movie.setDuration(request.getDuration());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setImageURL(request.getImageURL());
        movie.setScore(request.getScore());
        movie.setGenres(request.getGenres());
        movie.setDirector(director);
    }

    public Movie create(MovieRequest request) {
        Director director = validateAndGetDirector(request); // Valida aqui!

        Movie movie = new Movie();
        transferData(movie, request, director);

        return movieRepository.save(movie);
    }

    public Movie update(Long id, MovieRequest request) {
        Movie existingMovie = findById(id);
        Director director = validateAndGetDirector(request);

        transferData(existingMovie, request, director);

        return movieRepository.save(existingMovie);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filme de ID " + id + " não foi encontrado."));
    }

    public void deleteById(Long id) {
        Movie movie = findById(id);
        movieRepository.deleteById(id);
    }
}