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

    //Cria um objeto diretor e faz validação de campos
    public Director create(DirectorRequest request) {
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new FieldValidationException("O nome do diretor é obrigatório e não pode conter apenas espaços.");
        }

        if (request.getNationality() == null || request.getNationality().trim().isEmpty()) {
            throw new FieldValidationException("A nacionalidade do diretor é obrigatória e não pode conter apenar espaços.");
        }

        Director director = new Director();
        director.setName(request.getName().trim());
        director.setNationality(request.getNationality().trim());

        return directorRepository.save(director);
    }

    //Pega todos os objetos diretor
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    //Retorna um objeto diretor de acordo com o id
    public Director findById(Long id){
        return directorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Diretor de ID " + id + " não foi encontrado."));
    }
}