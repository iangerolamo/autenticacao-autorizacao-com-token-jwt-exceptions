package com.iangerolamo.autenticacao.autorizacao.com.token.jwt.services;

import com.iangerolamo.autenticacao.autorizacao.com.token.jwt.models.Cliente;
import com.iangerolamo.autenticacao.autorizacao.com.token.jwt.repositories.ClienteRepository;
import com.iangerolamo.autenticacao.autorizacao.com.token.jwt.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);

        if (cliente == null) {
            throw new UsernameNotFoundException(email);
        }

        System.out.println(cliente);


        return new UserSS(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
    }
}
