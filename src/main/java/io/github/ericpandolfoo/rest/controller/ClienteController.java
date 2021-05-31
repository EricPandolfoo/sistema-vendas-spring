package io.github.ericpandolfoo.rest.controller;

import io.github.ericpandolfoo.domain.entity.Cliente;
import io.github.ericpandolfoo.domain.repository.ClientesRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientesRepository clientesRepository;

    public ClienteController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(OK)
    public Cliente getClienteById(@PathVariable("id") Integer id) {
        return clientesRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @GetMapping(value = "/filtro")
    @ResponseStatus(OK)
    public List<Cliente> find(Cliente cliente) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(cliente, matcher);
        return clientesRepository.findAll(example);
    }


    @PostMapping(value = "/cadastrar")
    @ResponseStatus(CREATED)
    public Cliente salvarCliente(@RequestBody Cliente cliente) {
        return clientesRepository.save(cliente);
    }


    @DeleteMapping(value = "/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClienteById(@PathVariable("id") Integer id) {
        clientesRepository
                .findById(id)
                .map(c -> {
                    clientesRepository.delete(c);
                    return c;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }


    @PutMapping(value = "/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(
            @PathVariable("id") Integer id,
            @RequestBody @Valid Cliente cliente) {
        clientesRepository
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientesRepository.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }


}



