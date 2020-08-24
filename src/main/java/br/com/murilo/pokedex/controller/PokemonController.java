package br.com.murilo.pokedex.controller;

import br.com.murilo.pokedex.model.Pokemon;
import br.com.murilo.pokedex.model.PokemonEvent;
import br.com.murilo.pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
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

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PokemonEvent> getEvents() {
        return Flux.interval(Duration.ofSeconds(5)).map(val -> new PokemonEvent(val, "Evento de pokemon"));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Pokemon>> update(@PathVariable("id") String id,
                                                @RequestBody Pokemon requestPokemon) {
        return this.pokemonRepository.findById(id).flatMap(pokemon -> {
            pokemon.setNome(requestPokemon.getNome());
            pokemon.setPeso(requestPokemon.getPeso());
            pokemon.setHabilidade(requestPokemon.getHabilidade());
            pokemon.setCategoria(requestPokemon.getCategoria());
            return this.pokemonRepository.save(pokemon);
        })
        .map(pokemon -> ResponseEntity.ok(pokemon))
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id") String id) {
       return this.pokemonRepository.findById(id)
                .flatMap(pokemon ->
                        this.pokemonRepository.deleteById(id)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                                .defaultIfEmpty(ResponseEntity.notFound().build()));
    }
}
