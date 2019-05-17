package com.evandrosantos.cursomc.services;

import com.evandrosantos.cursomc.domain.Categoria;
import com.evandrosantos.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public Categoria buscar(Integer id) {
        Optional<Categoria> categoria = repository.findById(id);
        return categoria.orElse(null);
    }
}