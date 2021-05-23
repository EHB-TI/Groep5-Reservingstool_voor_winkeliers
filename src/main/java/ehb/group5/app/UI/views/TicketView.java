package ehb.group5.app.UI.views;


import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.DatabaseService;
import ehb.group5.app.backend.data.table.CompanyEntity;
import ehb.group5.app.backend.data.table.TicketEntity;
import ehb.group5.app.backend.data.table.TicketMessageEntity;

import java.sql.Timestamp;
import java.util.Date;

@Route("ticket")
@PageTitle("profiel")
@CssImport("./styles/ticket.css")
public class TicketView extends CommonLayout {

    /*
     Author: LAMSAKAM Zakaria
     email: zakaria.lamsakam@student.ehb.be
     */

    /*
    Ik heb het Vaadin documentatie gebruikt voor de textfield,de emailfield en de passwordfield.
    https://vaadin.com/docs/v14/
    */

    public TicketView(){

        CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("company");

        Div TicketContentdiv = new Div();
        TicketContentdiv.setId("ticketContentid");


        //Titel aanmaken
        TicketContentdiv.add(new H1("Nieuwe ticket"));

        // Probleem geven in de textfield
        TextField textField1 = new TextField("Het probleem");
        textField1.getStyle().set("minHeight", "10px");

        // Probleem bescrijven in detail in de Textarea
        TextArea textArea1 = new TextArea("Ticket");
        textArea1.getStyle().set("minHeight", "150px");
        textArea1.setPlaceholder("Beschrijf hier uw probleem");


        //knop aanmaken
        Button buttonT = new Button("Save");
        //Hier wordt er geinisialiseerd dat je ook met de enter van uw toetsenbord mag drukken.
        buttonT.addClickShortcut(Key.ENTER);


        //Hier gaan de textfield en de textarea worden toegevoegd aan de database namelijk aan de company.
        //De timestamp wordt gebruikt om de datum te initialiseren wanneer de informatie wordt toegevoegd in de database.
        buttonT.addClickListener(buttonClickEvent ->{
            TicketEntity ticket = new TicketEntity();
            ticket.setTitle(textField1.getValue());
            ticket.setCompany(company);
            ticket.setDateCreated(new Timestamp(new Date().getTime()));
            TicketMessageEntity ticketmessenger = new TicketMessageEntity();
            ticketmessenger.setMessage(textArea1.getValue());
            ticketmessenger.setCompany(company);
            ticketmessenger.setDate(new Timestamp(new Date().getTime()));
            ticketmessenger.setTicket(ticket);
            DatabaseService.getTicketMessageStore().insert(ticketmessenger);

            //Wanneer er geklikt wordt op de button gaan we gestuurd zijn naar de pagina van de klasse die geplaats word.
            UI.getCurrent().navigate(SupportAdminView.class);
        });

        //Alles in de TicketContentdiv toevoegen
        TicketContentdiv.add(textField1,textArea1);
        TicketContentdiv.add(buttonT);
        getContainer().add(TicketContentdiv);

    }

}
