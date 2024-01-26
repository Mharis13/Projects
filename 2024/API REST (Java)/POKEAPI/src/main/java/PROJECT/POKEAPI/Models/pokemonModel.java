package PROJECT.POKEAPI.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class pokemonModel {

    @Id
    private int PokedexNumber;

    private String PokemonName;

    private int Type1;

    private int Type2;

    public int getId() {
        return PokedexNumber;
    }

    public String getName() {
        return PokemonName;
    }

    public int getType() {
        return Type1;
    }

    public int getType2() {
        return Type2;
    }

    public void setId(int id) {
        this.PokedexNumber = id;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.PokemonName = name;
        }
    }

    public void setType(int type) {
        this.Type1 = type;
    }

    public void setType2(int type2) {
        this.Type2 = type2;
    }
}
