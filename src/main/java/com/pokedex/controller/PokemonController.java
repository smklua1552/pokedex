package com.pokedex.controller;

import com.pokedex.model.Pokemon;
import com.pokedex.service.PokemonService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemons")
    public String listarPokemons(@RequestParam(required = false) String nome, Model model) {
        List<Pokemon> pokemons;
        if (nome != null && !nome.isEmpty()) {
            pokemons = pokemonService.buscarPorNome(nome);
        } else {
            pokemons = pokemonService.listarPokemons();
        }
        model.addAttribute("pokemons", pokemons);
        return "pokemons";
    }

    @GetMapping("/pokemons/cadastrar")
    public String mostrarFormulario(Pokemon pokemon) {
        return "cadastrar";
    }

    @PostMapping("/pokemons/cadastrar")
    public String cadastrarPokemon(@Valid Pokemon pokemon, BindingResult result, Model model) {
        if (result.hasErrors()) {
            
            model.addAttribute("pokemon", pokemon);
            return "cadastrar"; 
        }
        pokemonService.salvar(pokemon);
        return "redirect:/pokemons";
    }

    @GetMapping("/pokemons/detalhes")
    public String detalhesPokemon(@RequestParam String nome, Model model) {
        List<Pokemon> pokemons = pokemonService.buscarPorNome(nome);

        if (pokemons != null && !pokemons.isEmpty()) {
            model.addAttribute("pokemon", pokemons.get(0));
            return "detalhes";
        } else {
            
            model.addAttribute("erro", "Pokémon não encontrado!");
            return "redirect:/pokemons";
        }
    }
}
