package com.evandrosantos.cursomc.services;

import com.evandrosantos.cursomc.domain.Categoria;
import com.evandrosantos.cursomc.domain.Produto;
import com.evandrosantos.cursomc.dto.produtos.FilterPageDTO;
import com.evandrosantos.cursomc.dto.produtos.ProdutoDTO;
import com.evandrosantos.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private CategoriaService categoriaService;

    public Page<ProdutoDTO> filterPage(FilterPageDTO dto, Integer page, Integer size, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaService.findCategoriasByIds(dto.getCategorias());
        Page<Produto> pageProdutos = repository.findDistinctByCategoriasInAndNomeLikeIgnoreCase(categorias, dto.getNome(), pageRequest);
        Page<ProdutoDTO> pageProdutosDTO = pageProdutos.map(ProdutoDTO::new);
        return pageProdutosDTO;
    }
}
