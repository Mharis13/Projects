package PROJECT.POKEAPI.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class pokemon_typesModel {
    @Id
    private int id;
    private String TypeName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }
}
