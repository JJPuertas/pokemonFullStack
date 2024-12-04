package dis.ufv.pokemonRestAPI.pokemonAPI.MainController;

import dis.ufv.pokemonRestAPI.pokemonAPI.LectorJson.lectorJson;
import dis.ufv.pokemonRestAPI.pokemonAPI.Pokemon.pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class mainController {
    @GetMapping("/pokemons")
    public ArrayList<pokemon> pokemons(){
        ArrayList<pokemon> listaPokemons = new lectorJson().leerJson("./src/main/resources/pokemonConId.json");
        return listaPokemons;
    }

    @GetMapping("/pokemon/porNombre/{nombre}")
    public ResponseEntity<pokemon> getPorNombre(@PathVariable String nombre){
        ArrayList<pokemon> listaPokemons = new lectorJson().leerJson("./src/main/resources/pokemonConId.json");
        pokemon encontrado = null;
        for (pokemon pokemon : listaPokemons) {
            if (pokemon.getName().equalsIgnoreCase(nombre)) {
                encontrado = pokemon;
            }
        }
        return new ResponseEntity<>(encontrado, HttpStatus.OK);
    }

    @GetMapping("/pokemon/porTipo/{tipo}")
    public ArrayList<pokemon>  getPortipo(@PathVariable String tipo){
        ArrayList<pokemon> listaPokemons = new lectorJson().leerJson("./src/main/resources/pokemonConId.json");
        ArrayList<pokemon> listaEncontrados = new ArrayList<>();
        for (pokemon pokemon : listaPokemons) {
            if (pokemon.getTipo1().equalsIgnoreCase(tipo) || pokemon.getTipo2().equalsIgnoreCase(tipo)) {
                listaEncontrados.add(pokemon);
            }
        }
        return listaEncontrados;
    }

}
