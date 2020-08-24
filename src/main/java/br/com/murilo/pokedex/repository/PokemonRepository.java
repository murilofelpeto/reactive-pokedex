package br.com.murilo.pokedex.repository;

import br.com.murilo.pokedex.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends ReactiveMongoRepository<Pokemon, String> {
}
