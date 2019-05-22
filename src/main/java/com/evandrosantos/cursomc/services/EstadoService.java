package com.evandrosantos.cursomc.services;

import com.evandrosantos.cursomc.domain.Estado;
import com.evandrosantos.cursomc.dto.estados.EstadoDTO;
import com.evandrosantos.cursomc.repositories.EstadoRepository;
import com.evandrosantos.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository repository;

    public Estado find(Integer id) {
        Optional<Estado> estado = repository.findById(id);
        return estado.orElseThrow(() -> {
            throw new ObjectNotFoundException(String.format("Objeto não encontrado! Id: %d, Tipo: Estado", id));
        });
    }

    public Estado insertOrUpdate(EstadoDTO estadoDTO) {
        if (estadoDTO.getId() != null)
            find(estadoDTO.getId());
        Estado estado = new Estado(estadoDTO);
        return repository.save(estado);
    }
}
