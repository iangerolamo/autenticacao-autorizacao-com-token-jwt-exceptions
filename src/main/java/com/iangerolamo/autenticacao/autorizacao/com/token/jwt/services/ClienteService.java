package com.iangerolamo.autenticacao.autorizacao.com.token.jwt.services;

import com.iangerolamo.autenticacao.autorizacao.com.token.jwt.exceptions.RegraNegocioException;
import com.iangerolamo.autenticacao.autorizacao.com.token.jwt.models.Cliente;
import com.iangerolamo.autenticacao.autorizacao.com.token.jwt.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public List<Cliente> consultarTodos() {
        return clienteRepository.findAll();
    }

   public Cliente consultarPorId(Integer id) {

        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new RegraNegocioException(
               "Objeto não encontrado! Id: " + id));
   }

    @Transactional
    public Cliente inserir(Cliente cliente) {
        validarCamposCliente(cliente);
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void atualizar(Cliente clienteAtualizado, Integer id) {
        validarCamposCliente(clienteAtualizado);
        Cliente cliente = consultarPorId(id);
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setSenha(clienteAtualizado.getSenha());
    }

    public void deletar(Integer id) {

        if (consultarPorId(id) != null) {
            clienteRepository.deleteById(id);
        }
    }

    public void validarCamposCliente(Cliente cliente) throws RegraNegocioException {

        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new RegraNegocioException("O campo nome não pode estar vazio");
        }

        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            throw new RegraNegocioException("O campo e-mail não pode estar vazio");
        }

        if (cliente.getSenha() == null || cliente.getSenha().isBlank()) {
            throw new RegraNegocioException("O campo senha não pode estar vazio");
        }
    }

}

