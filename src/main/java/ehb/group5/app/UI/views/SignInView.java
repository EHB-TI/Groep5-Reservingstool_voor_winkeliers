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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.backend.data.table.Company;
import lombok.val;

import java.net.HttpRetryException;

@Route("signup")
@PageTitle("Registreren als winkelier")
@CssImport("./styles/signin.css")

public class SignInView extends VerticalLayout {

    public SignInView(){
        Div signindiv = new Div();
        signindiv.setId("signinid");

        EmailField emailField = new EmailField();
        TextField passwordField = new TextField();
        TextField naamField = new TextField();
        TextField voornaamField = new TextField();
        TextField adresField = new TextField();
        NumberField postcodeField = new NumberField();
        NumberField gsmField = new NumberField();

        postcodeField.setWidth("100%");
        gsmField.setWidth("100%");
        adresField.setWidth("100%");

        H3 titel1 = new H3("Account informatie:");
        H3 titel2 = new H3("Winkel informatie:");
        H5 titel3 = new H5("Heeft u al een account ?");
        Hr hr1 = new Hr();

        emailField.setPlaceholder("E-mail");
        passwordField.setPlaceholder("Passwoord");
        naamField.setPlaceholder("Naam");
        voornaamField.setPlaceholder("Voornaam");
        adresField.setPlaceholder("Adres");
        postcodeField.setPlaceholder("Postcode/Gemeente");
        gsmField.setPlaceholder("Telefoon nummer");

        Button button = new Button("Sign up");
        Button button2 = new Button("Log dan hier in.");

        button2.addClickListener(event -> {
            UI.getCurrent().getPage().setLocation("login");
        });

        signindiv.add(new H1("Sign up"));
        signindiv.add(titel1);
        signindiv.add(emailField,passwordField);
        signindiv.add(hr1);
        signindiv.add(titel2);
        signindiv.add(naamField,voornaamField,adresField,postcodeField,gsmField,button);
        signindiv.add(titel3,button2);
        add(signindiv);
    }
}
