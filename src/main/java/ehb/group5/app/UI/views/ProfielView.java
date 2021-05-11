package ehb.group5.app.UI.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.DatabaseService;
import ehb.group5.app.backend.data.table.CompanyEntity;
import ehb.group5.app.backend.data.table.StoreEntity;

@Route("profiel")
@PageTitle("profiel")
@CssImport("./styles/profiel.css")
public class ProfielView extends CommonLayout {


        public ProfielView() {

            CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("company");
            StoreEntity store = (StoreEntity) VaadinSession.getCurrent().getAttribute("store");

            VerticalLayout v2 = new VerticalLayout();
            add(v2);
            v2.setId("v20");

            Button b1 = new Button("profiel bewerken");
            v2.add(b1);
            b1.addClickListener(event -> {
                UI.getCurrent().getPage().setLocation("profiel/edit");
            });
            b1.setId("button1");

            Button b2 = new Button("nieuwe medewerker");
            v2.add(b2);
            b2.setId("button1");

            Button b3 = new Button("verwijder medewerker");
            v2.add(b3);
            b3.setId("button1");

            VerticalLayout v1 = new VerticalLayout();
            add(v1);
            v1.setId("v10");

            v1.add(new H1("profiel"));
            v1.add(new H3("email: " + company.getEmail()));
            v1.add(new H3("voornaam: "));
            v1.add(new H3("achternaam:"));
            v1.add(new H3("wachtwoord: " + company.getPassword()));
            v1.add(new H3("Uw bedrijfsnummer: " + company.getId()));
            v1.add(new H3("Uw abonnement is geldig tot "));

            getContainer().add(v1,v2);

        }

}
