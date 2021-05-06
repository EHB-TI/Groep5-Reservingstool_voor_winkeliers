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

     /*
     Author: Zakaria Lamsakam
     email: zakaria.lamsakam@student.ehb.be
     */

    public ProfielBewerkenView(){

        Div div = new Div();

        //Titel aanmaken
        div.add(new H1("Profiel bewerken"));

        //Invoeren van gegevens aanmaken
        TextField labelField = new TextField();

        //Label initialiseren
        labelField.setLabel("Nieuwe Email");

        //De maximum limiet van letters implementeren
        labelField.setMaxLength(30);

        div.add(labelField);

        TextField labelField2 = new TextField();
        labelField2.setLabel("Nieuwe Voornaam");
        labelField2.setMaxLength(20);

        div.add(labelField2);

        TextField labelField3 = new TextField();
        labelField3.setLabel("Nieuwe Achternaam");
        labelField3.setMaxLength(20);

        div.add(labelField3);

        TextField labelField4 = new TextField();
        labelField4.setLabel("Nieuwe Wachtwoord");
        labelField4.setMaxLength(50);

        div.add(labelField4);

        //knop aanmaken
        Button button = new Button("Save");
        div.add(button);

        div.addClassName("centered-content");
        getContainer().add(div);



    }


}
