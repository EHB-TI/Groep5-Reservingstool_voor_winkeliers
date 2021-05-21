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
     Author: Zakaria Lamsakam
     email: zakaria.lamsakam@student.ehb.be
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
        buttonT.addClickShortcut(Key.ENTER);


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
            UI.getCurrent().navigate(SupportAdminView.class);
        });


        TicketContentdiv.add(textField1,textArea1);
        TicketContentdiv.add(buttonT);
        getContainer().add(TicketContentdiv);

    }

}
