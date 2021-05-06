package ehb.group5.app.UI.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import ehb.group5.app.UI.layouts.CommonLayout;

@Route("profiel/edit")
@PageTitle("ProfielBewerken")
@CssImport("./styles/ProfielBewerken.css")
public class ProfielBewerkenView extends CommonLayout {

    public ProfielBewerkenView(){


        getContainer().add(new H1("Profiel bewerken"));

        TextField labelField = new TextField();
        labelField.setLabel("Nieuwe Email");

        getContainer().add(labelField);

        TextField labelField2 = new TextField();
        labelField2.setLabel("Nieuwe Voornaam");

        getContainer().add(labelField2);

        TextField labelField3 = new TextField();
        labelField3.setLabel("Nieuwe Achternaam");

        getContainer().add(labelField3);

        TextField labelField4 = new TextField();
        labelField4.setLabel("Nieuwe Wachtwoord");

        getContainer().add(labelField4);

        Button button = new Button("Save");
        getContainer().add(button);

        addClassName("centered-content");


    }


}
