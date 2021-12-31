package com.iangerolamo.autenticacao.autorizacao.com.token.jwt.repositories;

import com.iangerolamo.autenticacao.autorizacao.com.token.jwt.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
