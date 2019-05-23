package com.evandrosantos.cursomc.repositories;

import com.evandrosantos.cursomc.domain.Categoria;
import com.evandrosantos.cursomc.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    @Transactional(readOnly = true)
    Page<Produto> findDistinctByCategoriasInAndNomeLikeIgnoreCase(List<Categoria> categorias, String nome, Pageable pageRequest);
}
