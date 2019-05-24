package com.evandrosantos.cursomc.services;

import com.evandrosantos.cursomc.domain.Cidade;
import com.evandrosantos.cursomc.domain.Cliente;
import com.evandrosantos.cursomc.domain.Endereco;
import com.evandrosantos.cursomc.dto.enderecos.EnderecoDTO;
import com.evandrosantos.cursomc.repositories.EnderecoRepository;
import com.evandrosantos.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private CidadeService cidadeService;

    public Endereco find(Integer id) throws ObjectNotFoundException {
        Optional<Endereco> endereco = repository.findById(id);
        return endereco.orElseThrow(() -> {
            throw new ObjectNotFoundException(String.format("Objeto não encontrado! Id: %d, Tipo: Endereco", id));
        });
    }

    public List<Endereco> insert(List<EnderecoDTO> enderecosDTO, Cliente cliente) {
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDTO enderecoDTO : enderecosDTO) {
            Cidade cidade = cidadeService.insertOrUpdate(enderecoDTO.getCidade());
            Endereco endereco = new Endereco(enderecoDTO, cliente);
            endereco.setCliente(cliente);
            endereco.setCidade(cidade);
            enderecos.add(repository.save(endereco));
        }
        return enderecos;
    }

    public void delete(List<Integer> enderecosId) {
        for (Integer id : enderecosId) {
            repository.deleteById(id);
        }
    }
}
