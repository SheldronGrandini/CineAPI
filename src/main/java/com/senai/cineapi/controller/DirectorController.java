package com.senai.cineapi.controller;

import com.senai.cineapi.dto.DirectorRequest;
import com.senai.cineapi.model.Director;
import com.senai.cineapi.service.DirectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/diretores")
public class DirectorController {
    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @PostMapping
    public ResponseEntity<Director> create(@RequestBody DirectorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(directorService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<Director>> findAll() {
        return ResponseEntity.ok(directorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> findById(@PathVariable Long id) {
        return ResponseEntity.ok(directorService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Director> update(@PathVariable Long id, @RequestBody DirectorRequest request) {
        return ResponseEntity.ok(directorService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        directorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}