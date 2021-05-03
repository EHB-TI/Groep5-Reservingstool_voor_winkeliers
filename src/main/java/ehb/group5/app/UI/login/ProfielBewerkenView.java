package ehb.group5.app.UI.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("profiel")
@PageTitle("ProfielBewerken")
@CssImport("./styles/ProfielBewerken.css")




public class ProfielBewerkenView extends VerticalLayout{

    public ProfielBewerkenView(){

        Div div = new Div();

        add(new H1("Profiel bewerken"));

        TextField labelField = new TextField();
        labelField.setLabel("Nieuwe Email");

        add(labelField);

        TextField labelField2 = new TextField();
        labelField2.setLabel("Nieuwe Voornaam");

        add(labelField2);

        TextField labelField3 = new TextField();
        labelField3.setLabel("Nieuwe Achternaam");

        add(labelField3);

        TextField labelField4 = new TextField();
        labelField4.setLabel("Nieuwe Wachtwoord");

        add(labelField4);

        Button button = new Button("Save");
        add(button);


    }


}