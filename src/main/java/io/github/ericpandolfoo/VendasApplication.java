package io.github.ericpandolfoo;

import io.github.ericpandolfoo.domain.entity.Cliente;
import io.github.ericpandolfoo.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientesRepository clientes) {
        return args -> {
            System.out.println("Salvando clientes!");
            clientes.save(new Cliente("Eric Pandolfo"));
            clientes.save(new Cliente("Joao Da Silva"));

            List<Cliente> listaClientes = clientes.findAll();
            listaClientes.forEach(System.out::println);

            String c = "Eric Pandolfo";
            boolean existe = clientes.existsByNome(c);
            System.out.println("Existe um cliente com o nome " + c + "? " + existe);

            listaClientes = clientes.findByNomeLike("Eric");
            listaClientes.forEach(System.out::println);



        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
