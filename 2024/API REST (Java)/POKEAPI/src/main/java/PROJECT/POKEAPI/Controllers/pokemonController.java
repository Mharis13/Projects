package PROJECT.POKEAPI.Controllers;

import PROJECT.POKEAPI.Models.pokemonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class pokemonController {

        @Autowired
        private PROJECT.POKEAPI.Services.pokemonService pokemonService;

        @GetMapping
        public List<pokemonModel> getAllPokemon() {
            return pokemonService.getAllPokemon();
        }

}
