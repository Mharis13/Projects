package PROJECT.POKEAPI.Repositories;

import PROJECT.POKEAPI.Models.pokemonModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface pokemonRespository extends JpaRepository<pokemonModel, Integer> {

}
