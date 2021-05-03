package ehb.group5.app.UI.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@Route("support")
@PageTitle("Support services")
@CssImport("./styles/support.css")

public class SupportView extends VerticalLayout {

    public SupportView(){

        add(new H1("Support services"));

        Button button = new Button("Ik heb een probleem met de facturatie");
        add(button);

        Button button2 = new Button("Ik heb een probleem met de betaling");
        add(button2);

        Button button3 = new Button("Ik heb een probleem met het bewerken van de informatie ");
        add(button3);

        Button button4 = new Button("Ik heb een probleem met de agenda");
        add(button4);

        Button button5 = new Button("Andere probleem");
        add(button5);
    }

}
