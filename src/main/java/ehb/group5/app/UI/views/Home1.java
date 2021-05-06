package ehb.group5.app.UI.chooseplan;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


//If you copy this route and try to do navigation with other routes this can cause an error.
@Route("views")
@PageTitle("Choose your payment")
@CssImport("./styles/style.css")


public class Home1 extends VerticalLayout {

    /*
      Author: De Vogel Ryan
      email: ryan.de.vogel@student.ehb.be
      Read the README.md bellow the file pom.xml
    */

    public Home1() {

        add(new H1("testing"));
    }
}

