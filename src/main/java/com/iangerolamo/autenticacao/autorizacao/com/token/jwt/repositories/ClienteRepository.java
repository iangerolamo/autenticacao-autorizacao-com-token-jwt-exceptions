package com.iangerolamo.autenticacao.autorizacao.com.token.jwt.repositories;

import com.iangerolamo.autenticacao.autorizacao.com.token.jwt.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
}
