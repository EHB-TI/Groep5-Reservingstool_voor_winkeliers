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
import ehb.group5.app.backend.data.table.AdminEntity;
import ehb.group5.app.backend.data.table.CompanyEntity;
import ehb.group5.app.backend.data.table.CustomerEntity;
import ehb.group5.app.backend.data.table.TicketEntity;



@Route("support/admin")
@PageTitle("Support/Admin")
@CssImport("./styles/SupportAdmin.css")

public class SupportAdminView extends CommonLayout {

     /*
     Authors: Jason Devedeleer, Arnaud Faille, De Vogel Ryan
     Read the README.md bellow the file pom.xml
    */

    public SupportAdminView() {


        CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("account");

        //main container
        Div mainDiv = new Div();
        mainDiv.addClassNames("main-content");

        //container in de main voor de verschillende tickets
        Div v1 = new Div();
        add(v1);
        v1.setId("v1");
        v1.add(new H1("Ticket"));

        Div v2 = new Div();
        add(v2);
        v2.setId("v2");

        Div titel = new Div();
        titel.setClassName("support-titel");

        Div message1 = new Div();
        message1.setClassName("message1");


        TextArea adminmessage = new TextArea();
        adminmessage.setClassName("adminmessage");
        adminmessage.setPlaceholder("Send message to customer");


        Button send = new Button("send", event-> {
            String message = adminmessage.getValue();

        });
        send.setClassName("button-send");


        message1.setId("hide-object");
        //logged in as company
        if (VaadinSession.getCurrent().getAttribute("account") instanceof CompanyEntity) {
            for(TicketEntity ticket : company.getTickets()) {
                Button b1 = new Button(ticket.getTitle(), event -> {
                    message1.setId("");
                    titel.removeAll();
                    message1.removeAll();

                    titel.add(new H3("Titel: " + ticket.getTitle()));

                    message1.add(new Paragraph("email: " + ticket.getCompany().getEmail()));
                    if(ticket.getStatus() == 1)
                        message1.add(new Paragraph("status: open"));
                    else
                        message1.add(new Paragraph("status: closed"));



                });
                b1.setId("button321");
                v1.add(b1);
                v2.add(titel, message1);
            }


        } else if (VaadinSession.getCurrent().getAttribute("account") instanceof AdminEntity) {
            AdminEntity admin = (AdminEntity) VaadinSession.getCurrent().getAttribute("account");
            //logged in as admin
        }


        //container in de main voor probleem beschrijving

        v2.add(adminmessage);
        v2.add(send);
        mainDiv.add(v1, v2);
        getContainer().add(mainDiv);
    }

}
