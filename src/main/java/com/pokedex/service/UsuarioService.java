package com.pokedex.service;

import com.pokedex.model.Usuario;
import com.pokedex.repository.UsuarioRepository;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

   
    public boolean autenticar(String username, String password) {
        return usuarioRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password)) 
                .isPresent();
    }

    public void salvar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
    public boolean cadastrar(Usuario usuario) {
    if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
        return false;
    }

    usuarioRepository.save(usuario);
    return true;
}

}
