package ehb.group5.app.UI.views;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.table.StoreEntity;

@Route("chooseplan")
@PageTitle("Choose your payment")
@CssImport("./styles/chooseplan.css")

public class ChoosePlanView extends VerticalLayout {

    /*
     Author: De Vogel Ryan
     Read the README.md bellow the file pom.xml
    */

    /*
     resources:
     - https://vaadin.com/forum/thread/16986951/redirecting-to-another-page-when-the-notification-x-is-clicked
     - https://vaadin.com/docs/v8/framework/articles/FindingTheCurrentUIAndPageAndVaadinSession
     */

    public ChoosePlanView() {
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


        Button button = new Button("1 maand");
        // Listen to button actions
        button.addClickListener(event -> {
            VaadinSession.getCurrent().setAttribute("parameter", "1");//Pass info to the next page
            UI.getCurrent().getPage().setLocation("payment");//navigate to next page
        });

        Button button1 = new Button("6 maanden");
        // Listen to button actions
        button1.addClickListener(event -> {
            VaadinSession.getCurrent().setAttribute("parameter", "6");//Pass info to the next page
            UI.getCurrent().getPage().setLocation("payment");//navigate to next page
        });

        Button button2 = new Button("12 maanden");
        // Listen to button actions
        button2.addClickListener(event -> {
            VaadinSession.getCurrent().setAttribute("parameter", "12");//Pass info to the next page
            UI.getCurrent().getPage().setLocation("payment");//navigate to next page
        });


        s.add(a);
        s.add(p);
        s.add(pp);
        s.add(button);



        s1.add(b);
        s1.add(p1);
        s1.add(pp1);
        s1.add(button1);
                                //Add items to div and div to page

        s2.add(c);
        s2.add(p2);
        s2.add(pp2);
        s2.add(button2);


        group.add(s);
        group.add(s1);
        group.add(s2);

        div.add(group);
        add(div);

    }

}
