package ehb.group5.app.UI.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;


import java.awt.*;

@Route("Support/Admin")
@PageTitle("Support/Admin")
@CssImport("./styles/style.css")

public class SupportAdminView extends HorizontalLayout{

    public SupportAdminView(){


        VerticalLayout v2 = new VerticalLayout();
        add(v2);

        v2.add(new H1("Ticket"));

        Button b1 = new Button("Ticket#1");
        v2.add(b1);

        Button b2 = new Button("Ticket#2");
        v2.add(b2);

        Button b3 = new Button("Ticket#3");
        v2.add(b3);

        Button b4 = new Button("Ticket#4");
        v2.add(b4);

        VerticalLayout v1 = new VerticalLayout();
        add(v1);

        v1.add(new H3("klantennummer"));
        v1.add(new H3("naam"));
        v1.add(new H3("beschrijving probleem"));


    }

}
