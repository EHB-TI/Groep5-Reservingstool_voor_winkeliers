package ehb.group5.app.UI.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import ehb.group5.app.UI.layouts.CommonLayout;


@Route("support")
@PageTitle("Support services")
@CssImport("./styles/support.css")
public class SupportView extends CommonLayout {

     /*
     Author: Zakaria Lamsakam
     email: zakaria.lamsakam@student.ehb.be
     */

    public SupportView(){

        Div div = new Div();

        //Titel aanmaken
        div.add(new H1("Support services"));

        //Knoppen aanmaken
        Button button = new Button("Ik heb een probleem met de facturatie");
        div.add(button);

        Button button2 = new Button("Ik heb een probleem met de betaling");
        div.add(button2);

        Button button3 = new Button("Ik heb een probleem met het bewerken van de informatie ");
        div.add(button3);

        Button button4 = new Button("Ik heb een probleem met de agenda");
        div.add(button4);

        Button button5 = new Button("Andere probleem");
        div.add(button5);

        div.addClassName("centered-content");

        getContainer().add(div);
    }

}
