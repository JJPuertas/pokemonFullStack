package dis.ufv.pokemonRestAPI.pokemonAPI.LectorJson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dis.ufv.pokemonRestAPI.pokemonAPI.Pokemon.pokemon;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class lectorJson {

    public ArrayList<pokemon> leerJson(String fichero){
        try {
            //lee el fichero que le pasemos y lo carga en un reader
            Reader reader = Files.newBufferedReader(Paths.get(fichero));
            // convierte el array JSON a un arraylist de users
            ArrayList<pokemon> listaPokemon =
                    new Gson().fromJson(reader, new TypeToken<ArrayList<pokemon>>() {}.getType());
            reader.close();// close reader
            return listaPokemon;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }

}
