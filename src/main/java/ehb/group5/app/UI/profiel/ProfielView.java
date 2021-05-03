package ehb.group5.app.UI.profiel;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;


import java.awt.*;

@Route("Profiel")
@PageTitle("Profiel")
@CssImport("./styles/style.css")
public class ProfielView extends VerticalLayout {


        public ProfielView() {
            add(new H1("profiel"));
            add(new H3("email:"));
            add(new H3("voornaam:"));
            add(new H3("achternaam:"));
            add(new H3("wachtwoord:"));
            add(new H3("Uw bedrijfsnummer:"));
            add(new H3("Uw abonnement is geldig tot "));



            Button b1 = new Button("profiel bewerken");
            add(b1);

            Button b2 = new Button("nieuwe medewerker");
            add(b2);

            Button b3 = new Button("verwijder medewerker");
            add(b3);

        }

}
