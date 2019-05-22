package com.evandrosantos.cursomc.resources;

import com.evandrosantos.cursomc.domain.Categoria;
import com.evandrosantos.cursomc.dto.generic.AlterarStatusDTO;
import com.evandrosantos.cursomc.dto.categorias.CategoriaDTO;
import com.evandrosantos.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "12") Integer size, @RequestParam(value = "direction", defaultValue = "ASC") String direction, @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        Page<CategoriaDTO> list = service.findPage(page, size, direction, orderBy);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Categoria categoria = service.find(id);
        return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        categoriaDTO.setId(null);
        Categoria categoria = service.insertOrUpdate(categoriaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        categoriaDTO.setId(id);
        service.insertOrUpdate(categoriaDTO);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Void> patch(@PathVariable Integer id, @RequestBody AlterarStatusDTO alterarStatusDTO) {
        alterarStatusDTO.setId(id);
        service.patch(alterarStatusDTO);
        return ResponseEntity.noContent().build();
    }
}
