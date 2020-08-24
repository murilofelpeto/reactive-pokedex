package br.com.murilo.pokedex;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class TestFlux {

    @Test
    public void testeFlux1() {
        Flux<String> flux = Flux.empty();
        flux.subscribe(System.out::println);
    }

    @Test
    public void testeFlux2() {
        Flux<String> flux = Flux.just("Pokemon");
        flux.subscribe(System.out::println);
    }

    @Test
    public void testeFlux3() {
        Flux<String> flux = Flux.just("Bulbassauro", "Squirtle", "Charmander");
        flux.subscribe(System.out::println);
    }
}
