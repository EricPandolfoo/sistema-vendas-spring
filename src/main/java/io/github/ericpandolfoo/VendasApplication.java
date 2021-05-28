package io.github.ericpandolfoo;

import io.github.ericpandolfoo.domain.entity.Cliente;
import io.github.ericpandolfoo.domain.entity.Pedido;
import io.github.ericpandolfoo.domain.repository.ClientesDAO;
import io.github.ericpandolfoo.domain.repository.PedidosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired ClientesDAO clientesDAO,
            @Autowired PedidosDAO pedidosDAO
    ) {
        return args -> {
            System.out.println("Salvando clientes!");
            Cliente eric = clientesDAO.save(new Cliente("Eric Pandolfo"));
            clientesDAO.save(new Cliente("Joao Da Silva"));


            //Criando um pedido e salvando ele
            Pedido p = new Pedido();
            p.setCliente(eric);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(1000));
            pedidosDAO.save(p);

            //Traz todos os pedidos de um cliente
            Cliente cliente = clientesDAO.findClienteFetchPedidos(eric.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());

            pedidosDAO.findByCliente(eric).forEach(System.out::println);


/*            //Traz todos os resultados da tabela clientes
            List<Cliente> listaClientes = clientesDAO.findAll();
            listaClientes.forEach(System.out::println);*/

            //verifica se o usuario existe na tabela clientes e retorna
            String c = "Eric Pandolfo";
            boolean existe = clientesDAO.existsByNome(c);
            System.out.println("Existe um cliente com o nome " + c + "? " + existe);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
