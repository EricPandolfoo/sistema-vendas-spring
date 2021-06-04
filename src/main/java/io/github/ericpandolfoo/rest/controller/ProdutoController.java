package io.github.ericpandolfoo.rest.controller;

import io.github.ericpandolfoo.domain.entity.Produto;
import io.github.ericpandolfoo.domain.repository.ProdutosRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/api/product")
public class ProdutoController {

    private ProdutosRepository repository;

    public ProdutoController(ProdutosRepository repository) {
        this.repository = repository;
    }


    @GetMapping(value = "/{id}")
    @ResponseStatus(OK)
    public Produto getProductById(@PathVariable("id") Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @GetMapping(value = "/filter")
    @ResponseStatus(OK)
    public List<Produto> getProductFilter(Produto produto) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(produto, matcher);
        return repository.findAll(example);
    }


    @PostMapping(value = "/create")
    @ResponseStatus(CREATED)
    public Produto createProduct(@RequestBody @Valid Produto produto) {
        return repository.save(produto);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteProductById(@PathVariable("id") Integer id) {
        repository
                .findById(id)
                .map(produto -> {
                    repository.delete(produto);
                    return produto;
                }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }


    @PutMapping(value = "/update/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateProductById(@PathVariable("id") Integer id,
                                  @RequestBody @Valid Produto produto) {

        repository
                .findById(id)
                .map(p -> {
                    produto.setId(p.getId());
                    repository.save(produto);
                    return p;
                }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }
}
