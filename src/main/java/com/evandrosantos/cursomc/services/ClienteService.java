package com.evandrosantos.cursomc.services;

import com.evandrosantos.cursomc.domain.Cliente;
import com.evandrosantos.cursomc.domain.Endereco;
import com.evandrosantos.cursomc.dto.clientes.ClienteDTO;
import com.evandrosantos.cursomc.dto.enderecos.EnderecoDTO;
import com.evandrosantos.cursomc.dto.generic.AlterarStatusDTO;
import com.evandrosantos.cursomc.repositories.ClienteRepository;
import com.evandrosantos.cursomc.services.exceptions.MyDataIntegrityViolationException;
import com.evandrosantos.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private EnderecoService enderecoService;

    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = repository.findAll();
        List<ClienteDTO> clientesDTO = clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
        return clientesDTO;
    }

    public Page<ClienteDTO> findPage(Integer page, Integer size, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        Page<Cliente> clientes = repository.findAll(pageRequest);
        Page<ClienteDTO> clientesDTO = clientes.map(ClienteDTO::new);
        return clientesDTO;
    }

    public Cliente find(Integer id) {
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.orElseThrow(() -> {
            throw new ObjectNotFoundException(String.format("Objeto não encontrado! Id: %d, Tipo: Cliente", id));
        });
    }

    @Transactional
    public Cliente insertOrUpdate(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        if (clienteDTO.getId() != null) {
            cliente = find(clienteDTO.getId());
            // Pega o Id dos endereços que estão sendo enviados no PUT -> Endereços a manter
            List<Integer> enderecosMantidos = clienteDTO.getEnderecos().stream().map(EnderecoDTO::getId).collect(Collectors.toList());
            // Pega o Id dos endereços que não estão sendo enviados -> Que não estão contidos na lista de endereços mantidos
            List<Integer> enderecosAExcluir = cliente.getEnderecos().stream().filter(endereco -> !enderecosMantidos.contains(endereco.getId())).map(Endereco::getId).collect(Collectors.toList());
            enderecoService.delete(enderecosAExcluir);
            cliente.setEnderecos(new ArrayList<>());
        }
        cliente.update(clienteDTO);
        cliente = repository.save(cliente);
        List<Endereco> enderecos = enderecoService.insert(clienteDTO.getEnderecos(), cliente);
        cliente.setEnderecos(enderecos);
        return cliente;
    }

    public void delete(Integer id) {
        Cliente cliente = find(id);
        try {
            repository.delete(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new MyDataIntegrityViolationException("Não é possível excluir um cliente que possui associações, somente inativar.");
        }
    }

    public void patch(AlterarStatusDTO alterarStatusDTO) {
        Cliente cliente = find(alterarStatusDTO.getId());
        if (!cliente.getStatus().equals(alterarStatusDTO.getStatus())) {
            cliente.setStatus(alterarStatusDTO.getStatus());
            repository.save(cliente);
        }
    }
}
