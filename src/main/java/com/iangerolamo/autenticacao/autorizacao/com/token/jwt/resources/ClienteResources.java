package com.iangerolamo.autenticacao.autorizacao.com.token.jwt.resources;

import com.iangerolamo.autenticacao.autorizacao.com.token.jwt.exceptions.RegraNegocioException;
import com.iangerolamo.autenticacao.autorizacao.com.token.jwt.models.Cliente;
import com.iangerolamo.autenticacao.autorizacao.com.token.jwt.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> consultarTodos() {
        List<Cliente> clientes = clienteService.consultarTodos();
        return ResponseEntity.ok().body(clientes);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity consultarPorId(@PathVariable Integer id) {

        try {
            Cliente cliente = clienteService.consultarPorId(id);
            return ResponseEntity.ok().body(cliente);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity inserir(@RequestBody Cliente novoCliente) {

        try {
            Cliente cliente = clienteService.inserir(novoCliente);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(cliente.getId()).toUri();

            return ResponseEntity.created(uri).build();
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletar(@PathVariable Integer id)  {
        try {
            clienteService.deletar(id);
            return ResponseEntity.noContent().build();

        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity atualizar(@RequestBody Cliente clienteAtualizado, @PathVariable Integer id) {

        try {
            clienteService.atualizar(clienteAtualizado, id);
            return ResponseEntity.noContent().build();

        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
