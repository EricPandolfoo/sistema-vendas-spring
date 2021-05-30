package io.github.ericpandolfoo;

import io.github.ericpandolfoo.domain.entity.Cliente;
import io.github.ericpandolfoo.domain.entity.Pedido;
import io.github.ericpandolfoo.domain.entity.Produto;
import io.github.ericpandolfoo.domain.repository.ClientesRepository;
import io.github.ericpandolfoo.domain.repository.PedidosRepository;
import io.github.ericpandolfoo.domain.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class VendasApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

   /* @Bean
    public CommandLineRunner commandLineRunner(
            @Autowired ClientesRepository clientesRepository,
            @Autowired PedidosRepository pedidosRepository,
            @Autowired ProdutosRepository produtoRepository) {
        return args -> {
            Cliente c = new Cliente("Eric Pandolfo", "ativo", "San Diego");
            clientesRepository.save(c);

            Pedido p = new Pedido(null, c, LocalDate.now(), BigDecimal.valueOf(1000));
            pedidosRepository.save(p);


            Produto product = new Produto("Produto top demais", BigDecimal.valueOf(100));
            produtoRepository.save(product);
        };
    }*/
}
