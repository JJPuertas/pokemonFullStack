package dis.pokemon.front;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import dis.pokemon.front.Pokemon.pokemon;
import dis.pokemon.front.Services.service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service
     *            The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired service service) {
        HorizontalLayout inputs = new HorizontalLayout();
        VerticalLayout results = new VerticalLayout();
        ComboBox<String> comboBox = new ComboBox<>("Selecciona uno...");
        comboBox.setAllowCustomValue(false); //este deja que el usuario escriba lo que quiera en la caja del comboBox. Si se pone a false no deja
        comboBox.setItems("Todos los pokemons", "Por Nombre", "Por tipo");
        comboBox.setHelperText("Selecciona el tipo de petición");

        Grid<pokemon> grid = new Grid<>(pokemon.class, true);
        grid.addColumn(pokemon::getName).setHeader("Nombre");
        grid.addColumn(pokemon::getAttack).setHeader("Ataque");
        grid.addColumn(pokemon::getDefense).setHeader("Defensa");
        grid.addColumn(pokemon::getTipo1).setHeader("Tipo1");
        grid.addColumn(pokemon::getSpeedDefense).setHeader("Tipo2");


        TextField datos = new TextField("Nombre/Tipo");
        datos.addThemeName("bordered");
        inputs.add(comboBox, datos);
        Button boton1 = new Button("Lee caracter",
                e -> {
                    String tipoPeticion = comboBox.getValue();
                    String dato = datos.getValue();
                    try {
                        results.removeAll();
                        if (tipoPeticion.equals("Por Nombre")){
                            results.add(service.leePokemonPorNombre(dato));
                        }
                        else if (tipoPeticion.equals("Por tipo")){
                            results.add(service.leePokemonPorTipo(dato));
                        }
                        else if (tipoPeticion.equals("Todos los pokemons")){
                            grid.setItems(service.leePokemons());
                            results.add(grid);
                        }

                    } catch (Exception ex) {
                    }
                });

        boton1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        boton1.addClickShortcut(Key.ENTER);
        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");

        add(inputs, boton1, results);
    }

}
