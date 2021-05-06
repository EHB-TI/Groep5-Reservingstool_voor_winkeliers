package ehb.group5.app.UI.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import ehb.group5.app.UI.layouts.CommonLayout;


@Route("support")
@PageTitle("Support services")
@CssImport("./styles/support.css")
public class SupportView extends CommonLayout {

    public SupportView(){

        getContainer().add(new H1("Support services"));


        Button button = new Button("Ik heb een probleem met de facturatie");
        getContainer().add(button);

        Button button2 = new Button("Ik heb een probleem met de betaling");
        getContainer().add(button2);

        Button button3 = new Button("Ik heb een probleem met het bewerken van de informatie ");
        getContainer().add(button3);

        Button button4 = new Button("Ik heb een probleem met de agenda");
        getContainer().add(button4);

        Button button5 = new Button("Andere probleem");
        getContainer().add(button5);
    }

}
