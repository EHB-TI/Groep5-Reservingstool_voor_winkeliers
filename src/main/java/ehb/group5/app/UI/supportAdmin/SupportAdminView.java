package ehb.group5.app.UI.supportAdmin;

import com.vaadin.flow.component.grid.Grid;
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

public class SupportAdminView extends VerticalLayout{

    public SupportAdminView(){
        add(new H1("Ticket"));
    }

}
