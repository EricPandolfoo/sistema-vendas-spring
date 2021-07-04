package io.github.ericpandolfoo.service.impl;

import io.github.ericpandolfoo.domain.entity.Cliente;
import io.github.ericpandolfoo.domain.entity.Usuario;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClienteServiceImplTest {

    @Test
    public void cadastrarCliente() {
        //CRIAR O CENARIO
        Cliente cliente = new Cliente(null, "Eric", "03184883108", null);

        //ACAO
        ClienteServiceImpl clienteService = new ClienteServiceImpl();
        String cpfFormatado = clienteService.transformarCpf("03184883108");

        //VERIFICACAO
        assertEquals("031.848.831-08", cpfFormatado);
    }
}