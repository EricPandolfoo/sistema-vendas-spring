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

            System.out.println("Atualizando clientes!");
            listaClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.save(c);
            });

            listaClientes = clientes.findAll();
            listaClientes.forEach(System.out::println);

            System.out.println("Buscando clientes");
            clientes.findByNomeLike("Eric").forEach(System.out::println);


            System.out.println("Deletar clientes!");
            clientes.findAll().forEach(c -> {
                clientes.delete(c);
            });

            listaClientes = clientes.findAll();
            if (listaClientes.isEmpty()) {
                System.out.println("Não tem mais cliente!");
            } else {
                listaClientes.forEach(System.out::println);
            }


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
