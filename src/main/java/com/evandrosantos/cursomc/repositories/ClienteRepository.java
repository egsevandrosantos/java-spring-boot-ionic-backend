package com.evandrosantos.cursomc.repositories;

import com.evandrosantos.cursomc.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Transactional(readOnly = true)
    Cliente findByEmailEquals(String email);

    @Transactional(readOnly = true)
    Cliente findByCpfCnpjEquals(String cpfCnpj);
}
