package ehb.group5.app.UI.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.DatabaseService;
import ehb.group5.app.backend.data.table.*;

import java.sql.Timestamp;
import java.util.Date;


@Route("support/admin")
@PageTitle("Mijn tickets")
@CssImport("./styles/SupportAdmin.css")

public class SupportAdminView extends CommonLayout {

     /*
     Authors: Jason Devedeleer, Arnaud Faille, De Vogel Ryan
     Read the README.md bellow the file pom.xml
    */
    private Div v2;

    public SupportAdminView() {
        //main container
        Div mainDiv = new Div();
        mainDiv.addClassNames("main-content");

        //container in de main voor de verschillende tickets
        Div v1 = new Div();
        add(v1);
        v1.setId("v1");
        v1.add(new H1("Ticket"));

        v2 = new Div();

        add(v2);
        v2.setId("v2");





        //logged in as company
        if (VaadinSession.getCurrent().getAttribute("account") instanceof CompanyEntity) {
            CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("account");
            for(TicketEntity ticket : company.getTickets()) {
                Button b1 = new Button(ticket.getTitle(), event -> {
                      showTicketContent(ticket);

                });
                b1.setId("button321");
                v1.add(b1);
            }


        } else if (VaadinSession.getCurrent().getAttribute("account") instanceof AdminEntity) {
            AdminEntity admin = (AdminEntity) VaadinSession.getCurrent().getAttribute("account");
            for(TicketEntity ticket : Ticket.getAllOpenedTickets()) {
                Button b1 = new Button(ticket.getTitle(), event -> {
                    showTicketContent(ticket);

                });
                b1.setId("button321");
                v1.add(b1);
            }
        }


        //container in de main voor probleem beschrijving
        mainDiv.add(v1, v2);
        getContainer().add(mainDiv);


    }

    public void showTicketContent(TicketEntity ticket){
        v2.removeAll();
        Div titel = new Div();
        titel.setClassName("support-titel");

        Div message1 = new Div();
        message1.setClassName("message1");

        message1.setId("");
        titel.removeAll();
        message1.removeAll();

        titel.add(new H3("Titel: " + ticket.getTitle()));

        message1.add(new Paragraph("email: " + ticket.getCompany().getEmail()));
        if(ticket.getStatus() == Ticket.Status.OPENED)
            message1.add(new Paragraph("status: open"));
        else
            message1.add(new Paragraph("status: closed"));

        v2.add(titel, message1);
        showMessages(ticket);
        addTicketMessage(ticket);
    }


    public void addTicketMessage(TicketEntity ticket){

        TextArea adminmessage = new TextArea();
        adminmessage.setClassName("adminmessage");
        adminmessage.setPlaceholder("Send message to customer");
        adminmessage.setAutofocus(true);

        Button send = new Button("send", event-> {
            String message = adminmessage.getValue();
            TicketMessageEntity entity = new TicketMessageEntity();

            entity.setMessage(message);
            if(VaadinSession.getCurrent().getAttribute("account") instanceof CompanyEntity) {
                CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("account");
                entity.setCompany(company);
            }
            else if (VaadinSession.getCurrent().getAttribute("account") instanceof AdminEntity)  {
                AdminEntity admin = (AdminEntity) VaadinSession.getCurrent().getAttribute("account");
                entity.setAdmin(admin);
            }

            entity.setDate(new Timestamp(new Date().getTime()));
            entity.setTicket(ticket);
            DatabaseService.getTicketMessageStore().insert(entity);
            Notification.show("De message is verstuurd !");
            showTicketContent(ticket);

        });

        Button changeStatus = new Button("status veranderen", event-> {
            if(ticket.getStatus() == Ticket.Status.OPENED)
                ticket.setStatus(Ticket.Status.CLOSED);
            else
                ticket.setStatus(Ticket.Status.OPENED);


            DatabaseService.getTicketStore().update(ticket);
            showTicketContent(ticket);
        });
        changeStatus.addClassNames("button-send");

        send.setClassName("button-send");


        if(ticket.getStatus() == Ticket.Status.OPENED)
            v2.add(adminmessage, send);

        v2.add(changeStatus);


    }

    public void showMessages(TicketEntity t){
        for (TicketMessageEntity ticketMessage : t.getTicketMessages()) {
            Div div = new Div();
            div.setClassName("message1");
            Paragraph messageOwner  = new Paragraph();
            Paragraph message = new Paragraph();
            message.addClassNames("message-content");
            if(ticketMessage.getCompany() != null)
                messageOwner.setText(ticketMessage.getCompany().getEmail() + " (" + ticketMessage.getDate() + ")");
            else if(ticketMessage.getAdmin() != null)
                messageOwner.setText(ticketMessage.getAdmin().getEmail()  + " (" + ticketMessage.getDate() + ")");
            else if(ticketMessage.getCustomer() != null)
                messageOwner.setText(ticketMessage.getCustomer().getEmail()  + " (" + ticketMessage.getDate() + ")");

            message.setText(ticketMessage.getMessage());
            div.add(messageOwner, message);
            v2.add(div);
        }
    }

}
