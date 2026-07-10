package com.senai.cineapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DirectorRequest {
    private String name;
    private String nationality;
}
