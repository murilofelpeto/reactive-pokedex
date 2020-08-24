package br.com.murilo.pokedex.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Pokemon {

    @Id
    private String id;
    private String nome;
    private String categoria;
    private String habilidade;
    private Double peso;

    public Pokemon(final String id, final String nome, final String categoria, final String habilidade, final Double peso) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.habilidade = habilidade;
        this.peso = peso;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(final String categoria) {
        this.categoria = categoria;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(final String habilidade) {
        this.habilidade = habilidade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(final Double peso) {
        this.peso = peso;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Pokemon)) return false;
        final Pokemon pokemon = (Pokemon) o;
        return Objects.equals(getId(), pokemon.getId()) &&
                Objects.equals(getNome(), pokemon.getNome()) &&
                Objects.equals(getCategoria(), pokemon.getCategoria()) &&
                Objects.equals(getHabilidade(), pokemon.getHabilidade()) &&
                Objects.equals(getPeso(), pokemon.getPeso());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCategoria(), getHabilidade(), getPeso());
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", habilidade='" + habilidade + '\'' +
                ", peso=" + peso +
                '}';
    }
}
