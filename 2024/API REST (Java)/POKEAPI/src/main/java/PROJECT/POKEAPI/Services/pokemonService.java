package PROJECT.POKEAPI.Services;

import PROJECT.POKEAPI.Models.pokemonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class pokemonService {

    @Autowired
    private PROJECT.POKEAPI.Repositories.pokemonRespository pokemonRespository;

    public List<pokemonModel> getAllPokemon() {
        return pokemonRespository.findAll();
    }
}
