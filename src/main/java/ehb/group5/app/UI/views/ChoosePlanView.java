package ehb.group5.app.UI.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import ehb.group5.app.UI.layouts.CommonLayout;

@Route("chooseplan")
@PageTitle("Choose your payment")
@CssImport("./styles/style.css")

public class ChoosePlanView extends CommonLayout {

    /*
     Author: De Vogel Ryan
     email: ryan.de.vogel@student.ehb.be
     Read the README.md bellow the file pom.xml
    */

    public ChoosePlanView() {
        add(new H1("Kies uw plan"));

        Div div = new Div();
        Section left = new Section();
        Section middel = new Section();
        Section right = new Section();
        H3 titel1 = new H3("1 maand");
        H3 titel2 = new H3("3 maanden");
        H3 titel3 = new H3("12 maanden");

        left.add(titel1);
        middel.add(titel2);
        right.add(titel3);

        for (int i = 0; i < 3; i++) {
            Paragraph p = new Paragraph("Lorem Ipsum");
            Paragraph p1 = new Paragraph("Lorem Ipsum");
            Paragraph p2 = new Paragraph("Lorem Ipsum");
            left.add(p);
            middel.add(p1);
            right.add(p2);
        }
        div.add(left);
        div.add(middel);
        div.add(right);
        getContainer().add(div);

    }

}
