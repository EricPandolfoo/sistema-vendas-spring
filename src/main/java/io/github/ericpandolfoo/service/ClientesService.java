package io.github.ericpandolfoo.service;

import io.github.ericpandolfoo.model.Cliente;
import io.github.ericpandolfoo.reposisory.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository repository;


    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        repository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente) {
        //aplica validações
    }


}
