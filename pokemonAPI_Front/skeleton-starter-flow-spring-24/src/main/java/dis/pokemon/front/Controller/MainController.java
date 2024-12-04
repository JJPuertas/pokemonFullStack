package dis.pokemon.front.Controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainController {
    private static final String urlPrefix = "http://pok:8081/%s";

    public String getPokemonPorNombre(String nombre) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "pokemon/porNombre/" + nombre);
        return sendRequest(fullUrl);
    }

    public String getPokemonPorTipo(String tipo) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "pokemon/porTipo/" + tipo);
        return sendRequest(fullUrl);
    }

    public String getPokemons() throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "pokemons");
        return sendRequest(fullUrl);
    }

    private String sendRequest(String url) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Error HTTP: " + response.statusCode() + " - " + response.body());
        }

        return response.body();
    }
}

