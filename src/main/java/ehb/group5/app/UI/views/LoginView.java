package ehb.group5.app.UI.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.backend.data.table.Company;
import lombok.val;

/*
     Author: ZOETARDT Craig
     email: craig.zoetardt@student.ehb.be
     */


@Route("login")
@PageTitle("Aanmelden als winkelier")
@CssImport("./styles/login.css")
public class LoginView extends VerticalLayout {


    public LoginView() {
        Div logindiv = new Div();
        logindiv.setId("logindiv");

        logindiv.add(new H1("Login"));
        // Creating id & password fields
        NumberField idField = new NumberField();
        PasswordField passwordField = new PasswordField();

        // Placing a placeholder to the fields
        idField.setPlaceholder("Bedrijfsnummer");
        passwordField.setPlaceholder("Wachtwoord");

        idField.setWidth("100%");
        Hr hr1 = new Hr();
        // Creating button
        Button button1 = new Button("Inloggen");
        button1.addClickShortcut(Key.ENTER);
        Button button2 = new Button("Sign up");

        H5 titel3 = new H5("Nog geen account ?");

        idField.setAutofocus(true);


        // Listen to button actions
        button1.addClickListener(event -> {
            if (idField.getValue()!= null){
                // Get the company by id
                val company = Company.getCompanyById((int) ((double) idField.getValue()));

                // Checking password
                if (company != null && passwordField.getValue().equals(company.getPassword())) {
                    // Route to dashboard view
                    UI.getCurrent().navigate(DashboardView.class);

                    // Saving company to the current session
                    VaadinSession.getCurrent().setAttribute("company", company);
                } else {
                    Notification.show("Wachtwoord of bedrijfsnummer niet geldig");
                }
            }
            else {
                Notification.show("Vakken zijn niet goed ingevuld");
            }


        });
        button2.addClickListener(event -> {
            UI.getCurrent().navigate(SignInView.class);
        });

        logindiv.add(idField, passwordField, hr1, button1, titel3, button2);
        add(logindiv);

    }

}