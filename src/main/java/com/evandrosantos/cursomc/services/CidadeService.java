package com.evandrosantos.cursomc.services;

import com.evandrosantos.cursomc.domain.Cidade;
import com.evandrosantos.cursomc.domain.Estado;
import com.evandrosantos.cursomc.dto.cidades.CidadeDTO;
import com.evandrosantos.cursomc.repositories.CidadeRepository;
import com.evandrosantos.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository repository;
    @Autowired
    private EstadoService estadoService;

    public Cidade find(Integer id) {
        Optional<Cidade> cidade = repository.findById(id);
        return cidade.orElseThrow(() -> {
            throw new ObjectNotFoundException(String.format("Objeto não encontrado! Id: %d, Tipo: Cidade", id));
        });
    }

    public Cidade insertOrUpdate(CidadeDTO cidadeDTO) {
        Estado estado = estadoService.insertOrUpdate(cidadeDTO.getEstado());
        if (cidadeDTO.getId() != null)
            find(cidadeDTO.getId());
        Cidade cidade = new Cidade(cidadeDTO);
        cidade.setEstado(estado);
        return repository.save(cidade);
    }
}
