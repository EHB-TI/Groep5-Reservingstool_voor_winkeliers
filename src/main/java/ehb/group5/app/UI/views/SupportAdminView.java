package ehb.group5.app.UI.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import ehb.group5.app.UI.layouts.CommonLayout;


import java.awt.*;

//gemaakt door jason devedeleer

@Route("support/admin")
@PageTitle("Support/Admin")
@CssImport("./styles/SupportAdmin.css")

public class SupportAdminView extends CommonLayout {

    public SupportAdminView(){

        //main container
        Div mainDiv = new Div();
        mainDiv.addClassNames("main-content");

        //container in de main voor de verschillende tickets
        Div v1 = new Div();
        add(v1);
        v1.setId("v1");

        v1.add(new H1("Ticket"));

        Button b1 = new Button("Ticket#1");
        v1.add(b1);
        b1.setId("button321");

        Button b2 = new Button("Ticket#2");
        v1.add(b2);
        b2.setId("button321");

        Button b3 = new Button("Ticket#3");
        v1.add(b3);
        b3.setId("button321");

        Button b4 = new Button("Ticket#4");
        v1.add(b4);
        b4.setId("button321");

        //container in de main voor probleem beschrijving
        Div v2 = new Div();
        add(v2);
        v2.setId("v2");

        v2.add(new H3("klantennummer"));
        v2.add(new H3("naam"));
        v2.add(new H3("beschrijving probleem"));

        mainDiv.add(v1, v2);
        getContainer().add(mainDiv);
    }

}
