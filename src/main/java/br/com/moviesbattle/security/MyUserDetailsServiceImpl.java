package br.com.moviesbattle.security;

import br.com.moviesbattle.domain.Usuario;
import br.com.moviesbattle.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario usuario = usuarioRepository.findByNomeUsuario(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        return criaSpringSecurityUsuario(usuario.getNomeUsuario(), usuario);
    }

    private org.springframework.security.core.userdetails.User criaSpringSecurityUsuario(String login, Usuario usuario) {
        List<GrantedAuthority> grantedAuthorities = Arrays.asList( new SimpleGrantedAuthority("ADMIN"));

        return new org.springframework.security.core.userdetails.User(usuario.getNomeUsuario(), usuario.getSenha(), grantedAuthorities);
    }
}
