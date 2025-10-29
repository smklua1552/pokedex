package com.pokedex.controller;

import com.pokedex.model.Usuario;
import com.pokedex.service.UsuarioService;
import com.pokedex.util.Endpoints;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(Endpoints.LOGIN)
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario()); 
        return Endpoints.VIEW_INDEX; 
    }

    @PostMapping(Endpoints.LOGIN)
    public String autenticar(@Valid Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return Endpoints.VIEW_INDEX;
        }

        boolean autenticado = usuarioService.autenticar(usuario.getUsername(), usuario.getPassword());
        if (!autenticado) {
            model.addAttribute("mensagemErro", "Email ou senha inválidos!");
            return Endpoints.VIEW_INDEX;
        }

        return "redirect:" + Endpoints.POKEMONS;
    }
}
