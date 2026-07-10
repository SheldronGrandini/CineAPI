package com.senai.cineapi.service;

import com.senai.cineapi.dto.DirectorRequest;
import com.senai.cineapi.exception.FieldValidationException;
import com.senai.cineapi.exception.ResourceNotFoundException;
import com.senai.cineapi.model.Director;
import com.senai.cineapi.repository.DirectorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    //Faz a validação do diretor
    private void validateDirector(DirectorRequest request) {
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new FieldValidationException("O nome do diretor é obrigatório.");
        }
        if (request.getNationality() == null || request.getNationality().trim().isEmpty()) {
            throw new FieldValidationException("A nacionalidade do diretor é obrigatória.");
        }
    }

    //Faz a atribuição de dados do request para o objeto
    private void transferData(Director director, DirectorRequest request) {
        director.setName(request.getName().trim());
        director.setNationality(request.getNationality().trim());
    }

    public Director create(DirectorRequest request) {
        validateDirector(request);

        Director director = new Director();
        transferData(director, request);

        return directorRepository.save(director);
    }

    public Director update(Long id, DirectorRequest request) {
        Director existingDirector = findById(id);
        validateDirector(request);

        transferData(existingDirector, request);

        return directorRepository.save(existingDirector);
    }

    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    public Director findById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diretor de ID " + id + " não foi encontrado."));
    }

    public void deleteById(Long id) {
        Director director = findById(id);
        directorRepository.deleteById(id);
    }
}