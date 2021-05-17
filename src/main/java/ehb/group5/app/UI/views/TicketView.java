package ehb.group5.app.UI.views;


import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.DatabaseService;
import ehb.group5.app.backend.data.table.CompanyEntity;

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

        // Probleem geven in de Textarea
        TextArea textArea = new TextArea("Het probleem");
        textArea.getStyle().set("minHeight", "10px");

        // Probleem bescrijven in detail in de Textarea
        TextArea textArea1 = new TextArea("Ticket");
        textArea1.getStyle().set("minHeight", "150px");
        textArea1.setPlaceholder("Beschrijf hier uw probleem");


        //knop aanmaken
        Button buttonT = new Button("Save");
        buttonT.addClickShortcut(Key.ENTER);
        /*
        //Nog met de backup van de pagina bezig.
        buttonT.addClickListener(buttonClickEvent ->{
            company.setEmail(textArea.getValue());
            company.setPassword(textArea1.getValue());
            DatabaseService.getCompaniesStore().insert(company);
        });
        */

        TicketContentdiv.add(textArea,textArea1);
        TicketContentdiv.add(buttonT);
        getContainer().add(TicketContentdiv);

    }

}
