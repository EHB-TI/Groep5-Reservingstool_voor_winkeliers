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

//gemaakt door jason devedeleer

@Route("profiel")
@PageTitle("profiel")
@CssImport("./styles/profiel.css")
public class ProfielView extends CommonLayout {


        public ProfielView() {

            CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("company");
            StoreEntity store = (StoreEntity) VaadinSession.getCurrent().getAttribute("store");

            //top container
            Div topDiv = new Div();
            topDiv.addClassNames("top-profiel-content");

            //main container
            Div mainDiv = new Div();
            mainDiv.addClassNames("profiel-content");

            //top-profiel container
            Div d1 = new Div();
            d1.setId("d1");

            d1.add(new H1("profiel"));

            //main-profiel-container
            VerticalLayout v1 = new VerticalLayout();
            add(v1);
            v1.setId("v10");

            v1.add(new H3("email: " + company.getEmail()));
            v1.add(new H3("Uw bedrijfsnummer: " + company.getId()));
            v1.add(new H3("Uw abonnement is geldig tot " + company.getSubscriptionExpiresDate()));

            //container voor de knoppen
            VerticalLayout v2 = new VerticalLayout();
            add(v2);
            v2.setId("v20");

            Button b1 = new Button("profiel bewerken");
            v2.add(b1);
            b1.addClickListener(event -> {
                UI.getCurrent().getPage().setLocation("profiel/edit");
            });
            b1.setId("button1");

            //aanmaken top container
            topDiv.add(d1);
            getContainer().add(topDiv);

            //aanmaken main container
            mainDiv.add(v1, v2);
            getContainer().add(mainDiv);
        }

}
