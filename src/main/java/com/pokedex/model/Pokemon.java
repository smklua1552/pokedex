package com.pokedex.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{pokemon.nome.notblank}")
    @Size(max = 50, message = "{pokemon.nome.size}")
    private String nome;

    @NotBlank(message = "{pokemon.tipo.notblank}")
    @Size(max = 30, message = "{pokemon.tipo.size}")
    private String tipo;

    @NotBlank(message = "{pokemon.descricao.notblank}")
    @Size(max = 255, message = "{pokemon.descricao.size}")
    private String descricao;

    @NotBlank(message = "{pokemon.imagem.notblank}")
    private String imagem;

    public Pokemon() {}

    public Pokemon(String nome, String tipo, String descricao, String imagem) {
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
}
