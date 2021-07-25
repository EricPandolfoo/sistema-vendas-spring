package io.github.ericpandolfoo.rest.controller;

import io.github.ericpandolfoo.domain.entity.Cliente;
import io.github.ericpandolfoo.domain.repository.ClientesRepository;
import io.github.ericpandolfoo.service.ClienteService;
import io.github.ericpandolfoo.service.impl.ClienteServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.var;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Api("Api Clientes")
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    private ClientesRepository clientesRepository;
    private ClienteService service;

    public ClienteController(ClientesRepository clientesRepository, ClienteServiceImpl service) {
        this.clientesRepository = clientesRepository;
        this.service = service;
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
    public Cliente salvarCliente(@RequestBody @Valid Cliente cliente) {
        return service.cadastrarCliente(cliente);
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



