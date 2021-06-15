package io.github.ericpandolfoo.service.impl;

import io.github.ericpandolfoo.domain.entity.Cliente;
import io.github.ericpandolfoo.domain.repository.ClientesRepository;
import io.github.ericpandolfoo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClientesRepository repository;


    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        cliente.setCpf(transformarCpf(cliente.getCpf()));
        return repository.save(cliente);
    }


    private String transformarCpf(String cpf) {
        try {
            MaskFormatter maskFormatter = new MaskFormatter("###.###.###-##");
            maskFormatter.setValueContainsLiteralCharacters(false);
            String cpfFormatado = maskFormatter.valueToString(cpf);
            return cpfFormatado;
        } catch (ParseException e) {
            return e.getMessage();
        }
    }

}
