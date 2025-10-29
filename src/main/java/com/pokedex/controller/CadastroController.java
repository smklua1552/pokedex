package com.pokedex.controller;

import com.pokedex.model.Usuario;
import com.pokedex.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CadastroController {

    private final UsuarioService usuarioService;

    public CadastroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    
    @GetMapping("/cadastro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro.html"; 
    }

    
    @PostMapping("/cadastro")
    public String cadastrar(@Valid Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cadastro";
        }

        boolean cadastrado = usuarioService.cadastrar(usuario);
        if (!cadastrado) {
            model.addAttribute("mensagemErro", "Usuário já existe!");
            return "cadastro";
        }

        model.addAttribute("mensagemSucesso", "Cadastro realizado com sucesso! Faça login.");
        return "redirect:/login";
    }
}
