package com.pokedex.service;

import com.pokedex.model.Pokemon;
import com.pokedex.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> listarPokemons() {
        return pokemonRepository.findAll();
    }

    
    public List<Pokemon> buscarPorNome(String nome) {
        return pokemonRepository.findByNomeIgnoreCase(nome);
    }

    public Pokemon salvar(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }
}
