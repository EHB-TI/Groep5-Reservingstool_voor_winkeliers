package ehb.group5.app.UI.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import ehb.group5.app.UI.layouts.CommonLayout;


import java.awt.*;

@Route("profiel")
@PageTitle("Profiel")
@CssImport("./styles/style.css")
public class ProfielView extends CommonLayout {


        public ProfielView() {
            getContainer().add(new H1("profiel"),
                    new H3("email:"),
                    new H3("voornaam:"),
                    new H3("achternaam:"));
            getContainer().add(new H3("wachtwoord:"));
            getContainer().add(new H3("Uw bedrijfsnummer:"));
            getContainer().add(new H3("Uw abonnement is geldig tot "));



            Button b1 = new Button("profiel bewerken");
            getContainer().add(b1);

            Button b2 = new Button("nieuwe medewerker");
            getContainer().add(b2);

            Button b3 = new Button("verwijder medewerker");
            getContainer().add(b3);

        }

}
