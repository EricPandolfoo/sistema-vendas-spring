package io.github.ericpandolfoo.rest.controller;

import io.github.ericpandolfoo.domain.entity.Usuario;
import io.github.ericpandolfoo.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {


    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer salvar(@RequestBody @Valid Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        usuarioService.salvar(usuario);
        return usuario.getId();
    }
}
