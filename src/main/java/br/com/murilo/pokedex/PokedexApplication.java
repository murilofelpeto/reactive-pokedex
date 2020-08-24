package br.com.murilo.pokedex;

import br.com.murilo.pokedex.model.Pokemon;
import br.com.murilo.pokedex.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations,
						   PokemonRepository repository) {

		return args -> {
			Flux<Pokemon> pokedexFlux = Flux.just(
					new Pokemon(null, "Bulbassauro", "Semente", "OverGrow", 6.09),
					new Pokemon(null, "Charmander", "Fogo", "Blaze", 5.03),
					new Pokemon(null, "Squirtle", "Água", "Torrente", 7.09)
			)
			.flatMap(repository::save);

			pokedexFlux.thenMany(repository.findAll()).subscribe(System.out::println);
		};
	}

}
