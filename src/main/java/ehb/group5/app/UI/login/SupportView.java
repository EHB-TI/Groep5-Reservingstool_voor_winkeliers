package ehb.group5.app.UI.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


@Route("support")
@PageTitle("Support services")
@CssImport("./styles/support.css")
@Theme(value = Lumo.class, variant = Lumo.DARK)

public class SupportView extends VerticalLayout {

    public SupportView(){

        add(new H1("Support services"));

        Button button = new Button("Ik heb een probleem met de facturatie");
        button.setHeight("200px");
        add(button);

        Button button2 = new Button("Ik heb een probleem met de betaling");
        button2.setHeight("200px");
        add(button2);

        Button button3 = new Button("Ik heb een probleem met het bewerken van de informatie ");
        button3.setHeight("200px");
        add(button3);

        Button button4 = new Button("Ik heb een probleem met de agenda");
        button4.setHeight("200px");
        add(button4);

        Button button5 = new Button("Andere probleem");
        button5.setHeight("200px");
        add(button5);


        addClassName("centered-content");
    }

}
