package ehb.group5.app.UI.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("profiel")
@PageTitle("ProfielBewerken")
@CssImport("./styles/ProfielBewerken.css")

public class ProfielBewerkenView extends VerticalLayout{

    public ProfielBewerkenView(){
        Button button = new Button("Nieuwe Email");
        add(button);

        Button button2 = new Button("Nieuwe Voornaam");
        add(button2);

        Button button3 = new Button("Nieuwe Achternaam");
        add(button3);

        Button button4 = new Button("Nieuwe Wachtwoord");
        add(button4);

        Button button5 = new Button("Save");
        add(button5);

    }
}
