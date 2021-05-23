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
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.backend.data.table.Admin;
import ehb.group5.app.backend.data.table.Company;
import ehb.group5.app.backend.security.PasswordAuthentication;
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
        setHeight("100vh");
        addClassName("login-content");


        Div logindiv = new Div();
        logindiv.setId("logindiv");

        logindiv.add(new H1("Login"));
        // Creating email & password fields
        EmailField emailField = new EmailField();
        PasswordField passwordField = new PasswordField();

        // Placing a placeholder to the fields
        emailField.setPlaceholder("Email");
        passwordField.setPlaceholder("Wachtwoord");

        emailField.setWidth("100%");
        Hr hr1 = new Hr();
        // Creating button
        Button loginButton = new Button("Inloggen");
        loginButton.addClickShortcut(Key.ENTER);
        Button redirectButton = new Button("Sign up");

        H5 titel3 = new H5("Nog geen account ?");

        emailField.setAutofocus(true);
        emailField.setErrorMessage("Hier moet een werkende e-mail adress staan.");

        // Listen to button actions
        loginButton.addClickListener(event -> {
            if (emailField.getValue()!= null){
                // Get the company by id
                val company = Company.getCompanyByEmail(emailField.getValue());
                val admin = Admin.getAdminByEmail(emailField.getValue());

                // Checking password
                if (company != null && new PasswordAuthentication().authenticate(passwordField.getValue().toCharArray(), company.getPassword())) {
                    // Saving company to the current session
                    VaadinSession.getCurrent().setAttribute("account", company);

                    // Route to dashboard view
                    UI.getCurrent().navigate(DashboardView.class);
                }
                else if (admin != null && new PasswordAuthentication().authenticate(passwordField.getValue().toCharArray(), admin.getPassword())) {


                    // Saving admin to the current session
                    VaadinSession.getCurrent().setAttribute("account", admin);
                    // Route to dashboard view
                    UI.getCurrent().navigate(SupportAdminView.class);
                }
                else {
                    Notification.show("Wachtwoord of email niet geldig");
                }
            }
            else {
                Notification.show("Vakken zijn niet goed ingevuld");
            }



        });
        redirectButton.addClickListener(event -> {
            UI.getCurrent().navigate(SignInView.class);
        });
// adding everything together to create the page
        logindiv.add(emailField, passwordField, hr1, loginButton, titel3, redirectButton);
        add(logindiv);

    }

}

