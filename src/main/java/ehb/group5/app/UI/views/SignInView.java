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
import ehb.group5.app.backend.data.DatabaseService;
import ehb.group5.app.backend.data.table.Company;
import ehb.group5.app.backend.data.table.CompanyEntity;
import ehb.group5.app.backend.data.table.StoreEntity;
import ehb.group5.app.backend.security.PasswordAuthentication;
import lombok.Data;
import lombok.val;

import java.net.HttpRetryException;

/*
     Author: ZOETARDT Craig
     email: craig.zoetardt@student.ehb.be
     inspiration: Vaadin TextField tips (https://www.youtube.com/watch?v=BPcMYbD9eCE)
     */

@Route("signup")
@PageTitle("Registreren als winkelier")
@CssImport("./styles/signin.css")

public class SignInView extends VerticalLayout {

    public SignInView() {

        setHeight("100vh");
        addClassName("signup-cont");

        Div signindiv = new Div();
        signindiv.setId("signinid");
        Div adressdiv = new Div();
        adressdiv.setId("adressid");
        // Creating  fields
        EmailField emailField = new EmailField();
        PasswordField passwordField = new PasswordField();
        TextField naamField = new TextField();
        TextField adresField = new TextField();
        NumberField postcodeField = new NumberField();
        TextField gsmField = new TextField();
        TextField postbusField = new TextField();

        postcodeField.setWidth("100%");
        gsmField.setWidth("100%");
        adresField.setWidth("75%");
        postbusField.setWidth("25%");

        H3 titel1 = new H3("Account informatie:");
        H3 titel2 = new H3("Winkel informatie:");
        H5 titel3 = new H5("Heeft u al een account ?");
        Hr hr1 = new Hr();
// Placing a placeholder to the fields
        postbusField.setPlaceholder("Postbus");
        emailField.setPlaceholder("E-mail");
        passwordField.setPlaceholder("Passwoord");
        naamField.setPlaceholder("Naam");
        adresField.setPlaceholder("Adres");
        postcodeField.setPlaceholder("Postcode/Gemeente");
        gsmField.setPlaceholder("Telefoon nummer");
// Placing mouse attribute
        emailField.getElement().setAttribute("title", "Voorbeeld: JohnSnow@gmail.com");
        postbusField.getElement().setAttribute("title", "Voorbeeld: 18A");
        naamField.getElement().setAttribute("title", "Voorbeeld: John Snow");
        adresField.getElement().setAttribute("title", "Voorbeeld: Nijverheidskaai 170");
        gsmField.getElement().setAttribute("title", "Voorbeeld: 02 523 37 37");
        postcodeField.getElement().setAttribute("title", "Voorbeeld: 1070 Anderlecht ");

        emailField.setAutofocus(true);
// Creating clear buttons
        postbusField.setClearButtonVisible(true);
        naamField.setClearButtonVisible(true);
        adresField.setClearButtonVisible(true);
        gsmField.setClearButtonVisible(true);
        postcodeField.setClearButtonVisible(true);
// making the fields required
        postbusField.setRequired(true);
        naamField.setRequired(true);
        adresField.setRequired(true);
        gsmField.setRequired(true);
        postcodeField.setRequiredIndicatorVisible(true);
        passwordField.setRequired(true);
// Creating error messages
        postbusField.setErrorMessage("Hier moet uw postbus staan");
        emailField.setErrorMessage("Hier moet een werkende e-mail adress staan.");
        passwordField.setErrorMessage("Uw wachtwoord moet minstens 6 characters bevatten");
        naamField.setErrorMessage("U moet uw naam hier ingeven");
        adresField.setErrorMessage("Uw adress moet hier ingevuld worden");
        postcodeField.setErrorMessage("uw postcode/gemeente moet hier staan");
        gsmField.setErrorMessage("Uw gsm nummer moet minstens 11 nummers bevatten of 14 als u +32 gebruikt");
// Making sure password is long enough and phone number too so its real
        passwordField.setMinLength(6);
        gsmField.setMaxLength(14);
        gsmField.setMinLength(11);

// Creating button
        Button signupButton = new Button("Sign up");
        signupButton.addClickShortcut(Key.ENTER);
        Button button2 = new Button("Log dan hier in.");
        // Listen to button actions
        button2.addClickListener(event -> {
            UI.getCurrent().navigate(LoginView.class);
        });
        signupButton.addClickListener(event -> {
//making sure everything is filled in
            if (emailField.getValue() != null
                    && passwordField.getValue() != null
                    && naamField.getValue() != null
                    && adresField.getValue() != null
                    && postbusField.getValue() != null
                    && postcodeField.getValue() != null
                    && gsmField.getValue() != null
                    && !emailField.getValue().isEmpty()
                    && !passwordField.getValue().isEmpty()
                    && !naamField.getValue().isEmpty()
                    && !adresField.getValue().isEmpty()
                    && !postbusField.getValue().isEmpty()
                    && postcodeField.getValue() != null
                    && !gsmField.getValue().isEmpty()) {

                // updating data on database

                if (DatabaseService.getCompaniesStore()
                        .count(CompanyEntity.class)
                        .where(CompanyEntity.EMAIL.eq(emailField.getValue()))
                        .get().value() == 0) {
                    CompanyEntity company = new CompanyEntity();
                    company.setEmail(emailField.getValue());
                    company.setPassword(new PasswordAuthentication().hash(passwordField.getValue().toCharArray()));

                    StoreEntity store = new StoreEntity();
                    store.setCompany(company);
                    store.setAdress(adresField.getValue());
                    store.setPostbus(postbusField.getValue());
                    store.setPostCode((int) (double)postcodeField.getValue());
                    store.setPhoneNumber(gsmField.getValue());
                    store.setName(naamField.getValue());

                   /* DatabaseService.getCompaniesStore().insert(company);*/
                    DatabaseService.getStoresStore().insert(store);

                    VaadinSession.getCurrent().setAttribute("account", company);
                    UI.getCurrent().navigate(ChoosePlanView.class);

                } else {
                    Notification.show("E-mail al in gebruik" );
                }
            } else {
                Notification.show("Alles is niet correct ingevuld.");
            }
        });
// adding everything together to create the page
        adressdiv.add(adresField, postbusField);
        signindiv.add(new H1("Sign up"));
        signindiv.add(titel1);
        signindiv.add(emailField, passwordField);
        signindiv.add(hr1);
        signindiv.add(titel2);
        signindiv.add(naamField, adressdiv, postcodeField, gsmField, signupButton);
        signindiv.add(titel3, button2);
        add(signindiv);
    }
}
