package ehb.group5.app.UI.views;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.table.StoreEntity;

@Route("chooseplan")
@PageTitle("Choose your payment")
@CssImport("./styles/chooseplan.css")

public class ChoosePlanView extends CommonLayout {

    /*
     Author: De Vogel Ryan
     email: ryan.de.vogel@student.ehb.be
     Read the README.md bellow the file pom.xml
    */

    public ChoosePlanView() {

        StoreEntity store = new StoreEntity();

        Div div = new Div();
        div.setClassName("chooseplan-container");
        H1 h1 = new H1("Kies uw plan");
        div.add(h1);

        Div group = new Div();
        group.setClassName("chooseplan-group");

        Section s = new Section();
        Section s1 = new Section();
        Section s2 = new Section();
        s.setClassName("chooseplan-section");
        s1.setClassName("chooseplan-section");
        s2.setClassName("chooseplan-section");
        H3 a = new H3("1 maand");
        H3 b = new H3("3 maanden");
        H3 c = new H3("12 maanden");
        Paragraph p = new Paragraph("Lorem Ipsum");
        Paragraph p1 = new Paragraph("Lorem Ipsum");
        Paragraph p2 = new Paragraph("Lorem Ipsum");
        Paragraph pp = new Paragraph("* 30 day money back guarantie");
        Paragraph pp1 = new Paragraph("* 30 day money back guarantie");
        Paragraph pp2 = new Paragraph("* 30 day money back guarantie");
        Button xd = new Button("Kies dit plan");
        xd.addClickListener(
        event -> {
            UI.getCurrent().getPage().setLocation("http://www.google.com");
        });
        Button xd1 = new Button("Kies dit plan");
        Button xd2 = new Button("Kies dit plan");

        s.add(a);
        s.add(p);
        s.add(pp);
        s.add(xd);

        s1.add(b);
        s1.add(p1);
        s1.add(pp1);
        s1.add(xd1);

        s2.add(c);
        s2.add(p2);
        s2.add(pp2);
        s2.add(xd2);

        group.add(s);
        group.add(s1);
        group.add(s2);

        div.add(group);
        add(div);

    }

}
