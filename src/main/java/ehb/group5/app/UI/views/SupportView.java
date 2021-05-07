package ehb.group5.app.UI.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
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

        //notificatie tonen wanneer er op een button wordt gedrukt.
        Dialog dialog = new Dialog();
        dialog.add(new Text("Bel deze telefoon voor support : 0124592598"));

        dialog.setWidth("400px");
        dialog.setHeight("150px");

        button.addClickListener(event -> dialog.open());


        Button button2 = new Button("Ik heb een probleem met de betaling");
        div.add(button2);

        Dialog dialog2 = new Dialog();
        dialog2.add(new Text("Bel deze telefoon voor support: 0124392594"));

        dialog.setWidth("400px");
        dialog.setHeight("150px");

        button2.addClickListener(event -> dialog.open());

        Button button3 = new Button("Ik heb een probleem met het bewerken van de informatie ");
        div.add(button3);



        Dialog dialog3 = new Dialog();
        dialog3.add(new Text("Bel deze telefoon voor support: 0184592599"));

        dialog.setWidth("400px");
        dialog.setHeight("150px");

        button3.addClickListener(event -> dialog.open());

        Button button4 = new Button("Ik heb een probleem met de agenda");
        div.add(button4);


        Dialog dialog4 = new Dialog();
        dialog4.add(new Text("Bel deze telefoon voor support: 0124692298"));

        dialog.setWidth("400px");
        dialog.setHeight("150px");

        button4.addClickListener(event -> dialog.open());

        Button button5 = new Button("Andere probleem");
        div.add(button5);



        Dialog dialog5 = new Dialog();
        dialog5.add(new Text("Bel deze telefoon voor support: 0174992568"));

        dialog.setWidth("400px");
        dialog.setHeight("150px");

        button5.addClickListener(event -> dialog.open());

        div.addClassName("centered-content");
        getContainer().add(div);
    }

}
