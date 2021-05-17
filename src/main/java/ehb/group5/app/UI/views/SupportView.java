package ehb.group5.app.UI.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.DatabaseService;
import ehb.group5.app.backend.data.table.CompanyEntity;


@Route("support")
@PageTitle("Support services")
@CssImport("./styles/support.css")
public class SupportView extends CommonLayout {

     /*
     Author: Zakaria Lamsakam
     email: zakaria.lamsakam@student.ehb.be
     */

    public SupportView(){

        CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("company");

        Div Supportdiv = new Div();
        Supportdiv.setId("supportid");

        //Titel aanmaken
        Supportdiv.add(new H1("Support services"));

        //Knoppen aanmaken
        Button button = new Button("Ik heb een probleem met de facturatie");
        Supportdiv.add(button);

        //notificatie tonen wanneer er op een button wordt gedrukt.
        Dialog dialog = new Dialog();
        dialog.add(new Text("Uw factuur bereikt zijn bestemming niet door onjuiste gegevens. Vraag vooraf alle gegevens op, begin met het e-mailadres van de verantwoordelijke voor de betaling van de facturen.\"" +
                ""+" Andere problemen bel dan deze telefoonnummer voor support: 0124592598"),
               new Button("Close", e -> dialog.close()));

        dialog.setWidth("600px");
        dialog.setHeight("250px");

        button.addClickListener(event -> dialog.open());


        Button button2 = new Button("Ik heb een probleem met de betaling");
        Supportdiv.add(button2);

        Dialog dialog2 = new Dialog();
        dialog2.add(new Text("U kan dit oplossen door één of meerdere minder dringende overschrijvingen uit te vinken.\n" +
                " Vervolgens tekent u enkel de aangevinkte verrichtingen. "+" Andere problemen bel dan deze telefoonnummer voor support: 0124392594"),
                    new Button("Close", e -> dialog2.close()));

        dialog.setWidth("600px");
        dialog.setHeight("250px");

        button2.addClickListener(event -> dialog2.open());

        Button button3 = new Button("Ik heb een probleem met het bewerken van de informatie ");
        Supportdiv.add(button3);



        Dialog dialog3 = new Dialog();
        dialog3.add(new Text("BIC code (Bank Identification Code) is een unieke code die één welbepaalde bank identificeert en bestaat meestal uit 8 of 11 tekens."
                +" Andere problemen bel dan deze telefoonnummer voor support: 0184592599"),
                new Button("Close", e -> dialog3.close()));

        dialog.setWidth("600px");
        dialog.setHeight("250px");

        button3.addClickListener(event -> dialog3.open());

        Button button4 = new Button("Ik heb een probleem met de agenda");
        Supportdiv.add(button4);


        Dialog dialog4 = new Dialog();
        dialog4.add(new Text("Zorg ervoor dat je verbinding met internet hebt, controleer of de agenda zichtbaar is, zorg dat nieuwe afspraken worden toegevoegd aan je Google Agenda."
                +" Andere problemen bel dan deze telefoonnummer voor support: 0124692298"),
                new Button("Close", e -> dialog4.close()));

        dialog.setWidth("600px");
        dialog.setHeight("250px");

        button4.addClickListener(event -> dialog4.open());

        Button button5 = new Button("Andere probleem");
        Supportdiv.add(button5);
        button5.addClickListener(buttonClickEvent ->{
            UI.getCurrent().navigate(TicketView.class);
        });



        Supportdiv.addClassName("centered-content");
        getContainer().add(Supportdiv);
    }

}
