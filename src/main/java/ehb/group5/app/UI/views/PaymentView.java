package ehb.group5.app.UI.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.DatabaseService;
import ehb.group5.app.backend.data.table.CompanyEntity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


@Route("payment")
@PageTitle("payment")
@CssImport("./styles/payment.css")

public class PaymentView extends VerticalLayout {

    /*
     Author: De Vogel Ryan
     Read the README.md bellow the file pom.xml
    */

    public PaymentView(){
        checkcontent();

    }

    public void checkcontent(){
        Div container = new Div();
        container.setClassName("payment-container-parent");
        if(VaadinSession.getCurrent().getAttribute("parameter") == "1"){//Check the info from the previous page
            H1 h = new H1("Uw totaal is: €20");
            h.setClassName("payment-heading");
            container.add(h);
            createHtml("€20", container);
        }
        else if(VaadinSession.getCurrent().getAttribute("parameter") == "6"){//Check the info from the previous page
            H1 h = new H1("Uw totaal is: €38");
            h.setClassName("payment-heading");
            container.add(h);
            createHtml("€38", container);
        }
        else if(VaadinSession.getCurrent().getAttribute("parameter") == "12"){//Check the info from the previous page
            H1 h = new H1("Uw totaal is: €50");
            h.setClassName("payment-heading");
            container.add(h);
            createHtml("€50", container);
        }

    }

    public void createHtml(String value, Div container){

        CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("account");


        H3 h = new H3("Betaal veilig met een credit card of PayPal");
        h.setClassName("payment-heading");
        container.add(h);

        Div choose = new Div();
        choose.setClassName("payment-image-div");
        Image image = new Image("/frontend/creditcard.png", "paypal");
        image.setClassName("payment-image");
        H1 paypal = new H1("PayPal");
        paypal.setClassName("payment-paypal");
        choose.add(image);
        choose.add(paypal);
        container.add(choose);

        TextField name = new TextField();
        name.setClassName("payment-textfield");
        name.setPlaceholder("Name on the card");
        container.add(name);


        TextField number = new TextField();
        number.setClassName("payment-textfield");
        number.setPlaceholder("Card number");
        container.add(number);

        Div d = new Div();
        d.setClassName("payment-container");

        TextField tijd = new TextField();
        tijd.setClassName("payment-textfield-small");
        tijd.setPlaceholder("MM/YY");

        TextField Cvc = new TextField();
        Cvc.setClassName("payment-textfield-small");
        Cvc.setPlaceholder("Cvc");

        TextField Zip = new TextField();
        Zip.setClassName("payment-textfield-small");
        Zip.setPlaceholder("Zip/Postal code");

        d.add(tijd);
        d.add(Cvc);
        d.add(Zip);
        container.add(d);

        Div buttondiv = new Div();
        buttondiv.setClassName("buttondiv");
        Button button = new Button("Betaal " + value);
        button.setClassName("payment-button");
        button.addClickListener(event -> {
            if(name.getValue().length() < 1 || number.getValue().length() < 8 ||
                    tijd.getValue().length() < 4 || Cvc.getValue().length() < 3 || Zip.getValue().length() < 4){
                Notification.show("Je moet alles invullen !");
            }
            else {
                Notification.show("Goedzo !");

                Calendar date = Calendar.getInstance();
                date.setTime(new Date());

                if(VaadinSession.getCurrent().getAttribute("parameter") == "1") {
                    date.add(Calendar.MONTH, 1 /* AANTAL MAANDEN*/);
                }
                else if(VaadinSession.getCurrent().getAttribute("parameter") == "6") {
                    date.add(Calendar.MONTH, 6 /* AANTAL MAANDEN*/);
                }

                else if(VaadinSession.getCurrent().getAttribute("parameter") == "12") {
                    date.add(Calendar.MONTH, 12 /* AANTAL MAANDEN*/);
                }

                company.setSubscriptionExpiresDate(new Timestamp(date.getTime().getTime()));

                DatabaseService.getCompaniesStore().update(company);

                UI.getCurrent().navigate(DashboardView.class);
            }
        });
        buttondiv.add(button);
        container.add(buttondiv);
        add(container);


    }


}
