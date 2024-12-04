package dis.pokemon.front.Services;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dis.pokemon.front.Controller.MainController;
import dis.pokemon.front.Pokemon.pokemon;
import org.springframework.stereotype.Service;

@Service
public class service implements Serializable {


    public String leePokemonPorNombre(String nombre) throws URISyntaxException, IOException, InterruptedException {
        MainController api = new MainController();
        return api.getPokemonPorNombre(nombre);
    }
    public String leePokemonPorTipo(String tipo) throws URISyntaxException, IOException, InterruptedException {
        MainController api = new MainController();
        return api.getPokemonPorTipo(tipo);
    }

    public ArrayList<pokemon> leePokemons() throws URISyntaxException, IOException, InterruptedException {
        MainController api = new MainController();
        String resultsAPI = api.getPokemons();
        Gson gson = new Gson();
        ArrayList<pokemon> lista = gson.fromJson(resultsAPI,new TypeToken<ArrayList<pokemon>>(){}.getType());
        return lista;
    }
}


