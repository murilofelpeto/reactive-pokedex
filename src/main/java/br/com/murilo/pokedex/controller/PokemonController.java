package br.com.murilo.pokedex.controller;

import br.com.murilo.pokedex.model.Pokemon;
import br.com.murilo.pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonController( PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @GetMapping
    public Flux<Pokemon> findAll() {
        return this.pokemonRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pokemon>> findById(@PathVariable("id") String id) {
        return this.pokemonRepository.findById(id)
                .map(pokemon -> ResponseEntity.ok(pokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Pokemon>> save(@RequestBody Pokemon requestPokemon) {
        return this.pokemonRepository.save(requestPokemon)
                .map(pokemon -> new ResponseEntity<>(pokemon, HttpStatus.CREATED));
    }
}
