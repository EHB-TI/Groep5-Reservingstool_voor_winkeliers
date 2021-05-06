package ehb.group5.app.UI.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ehb.group5.app.UI.layouts.CommonLayout;


//If you copy this route and try to do navigation with other routes this can cause an error.
@Route("views/Chooseplan")
@PageTitle("Choose your payment")
@CssImport("./styles/style.css")


public class HomeView extends CommonLayout {

    /*
      Author: De Vogel Ryan
      email: ryan.de.vogel@student.ehb.be
      Read the README.md bellow the file pom.xml
    */

    public HomeView() {

        getContainer().add(new H1("home"));
    }
}

