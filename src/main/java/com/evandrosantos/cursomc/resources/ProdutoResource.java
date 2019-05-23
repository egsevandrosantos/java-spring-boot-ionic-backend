package com.evandrosantos.cursomc.resources;

import com.evandrosantos.cursomc.dto.produtos.FilterPageDTO;
import com.evandrosantos.cursomc.dto.produtos.ProdutoDTO;
import com.evandrosantos.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
    @Autowired
    private ProdutoService service;

    @RequestMapping(value = "/filterPage", method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> filterPage(@RequestBody FilterPageDTO dto, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "12") Integer size, @RequestParam(value = "direction", defaultValue = "ASC") String direction, @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        Page<ProdutoDTO> produtoDTOPage = service.filterPage(dto, page, size, direction, orderBy);
        return ResponseEntity.ok().body(produtoDTOPage);
    }
}
