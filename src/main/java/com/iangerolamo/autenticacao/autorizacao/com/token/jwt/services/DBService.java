package com.iangerolamo.autenticacao.autorizacao.com.token.jwt.services;

import com.iangerolamo.autenticacao.autorizacao.com.token.jwt.models.Cliente;
import com.iangerolamo.autenticacao.autorizacao.com.token.jwt.models.enums.Perfil;
import com.iangerolamo.autenticacao.autorizacao.com.token.jwt.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private ClienteRepository clienteRepository;

    public void instantiateTestDatabase() {

        Cliente cli1 = new Cliente(null, "Ian", "ian@gmail.com", pe.encode("123"));
        cli1.addPerfil(Perfil.ADMIN);
        Cliente cli2 = new Cliente(null, "Anakin", "anakin@gmail.com", pe.encode("123"));
        Cliente cli3 = new Cliente(null, "Frodo", "frodo@gmail.com", pe.encode("123"));
        Cliente cli4 = new Cliente(null, "Luke", "luke@gmail.com", pe.encode("123"));
        Cliente cli5 = new Cliente(null, "Neo", "neo@gmail.com", pe.encode("123"));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));

    }
}
