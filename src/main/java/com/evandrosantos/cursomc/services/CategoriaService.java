package com.evandrosantos.cursomc.services;

import com.evandrosantos.cursomc.domain.Categoria;
import com.evandrosantos.cursomc.dto.categorias.AlterarStatusDTO;
import com.evandrosantos.cursomc.dto.categorias.CategoriaDTO;
import com.evandrosantos.cursomc.repositories.CategoriaRepository;
import com.evandrosantos.cursomc.services.exceptions.MyDataIntegrityViolationException;
import com.evandrosantos.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Integer id) {
        Optional<Categoria> categoria = repository.findById(id);
        return categoria.orElseThrow(() -> {
            throw new ObjectNotFoundException(String.format("Objeto não encontrado! Id: %d, Tipo: Categoria", id));
        });
    }

    public List<CategoriaDTO> findAll() {
        List<Categoria> categorias = repository.findAll();
        List<CategoriaDTO> categoriasDTO = categorias.stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
        return categoriasDTO;
    }

    public Page<CategoriaDTO> findPage(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
        Page<Categoria> pageCategorias = repository.findAll(pageRequest);
        Page<CategoriaDTO> pageCategoriasDTO = pageCategorias.map(categoria -> new CategoriaDTO(categoria));
        return pageCategoriasDTO;
    }

    public Categoria insertOrUpdate(Categoria categoria) {
        if (categoria.getId() != null)
            find(categoria.getId());
        return repository.save(categoria);
    }

    public void delete(Integer id) {
        Categoria categoria = find(id);
        try {
            repository.delete(categoria);
        } catch (DataIntegrityViolationException e) {
            throw new MyDataIntegrityViolationException("Não é possível excluir uma categoria que possui produtos associados, somente inativar.");
        }
    }

    public void patch(AlterarStatusDTO alterarStatusDTO) {
        Categoria categoria = find(alterarStatusDTO.getId());
        if (!categoria.getStatus().equals(alterarStatusDTO.getStatus())) {
            categoria.setStatus(alterarStatusDTO.getStatus());
            repository.save(categoria);
        }
    }
}
