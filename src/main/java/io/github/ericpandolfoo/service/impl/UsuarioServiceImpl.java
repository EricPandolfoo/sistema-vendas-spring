package io.github.ericpandolfoo.service.impl;

import io.github.ericpandolfoo.domain.entity.Usuario;
import io.github.ericpandolfoo.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UsuarioRepository repository;


    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }


    /*  Carregar o usuário na base de dados através do seu login.
    **  Lá na classe SecurityConfig, o método 'configure' do 'AuthenticationManagerBuilder' autentifica o usuário
    **  passando esta instância, no caso, 'UsuarioServiceImpl' que implementa a interface UserDetailsService
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados"));

        String[] roles = usuario.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();

    }
}
