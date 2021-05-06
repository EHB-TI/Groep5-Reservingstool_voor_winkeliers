package ehb.group5.app.UI.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
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

public class SignIn_view extends VerticalLayout {

    public SignIn_view(){
        EmailField emailField = new EmailField();
        TextField passwordField = new TextField();
        TextField naamField = new TextField();
        TextField voornaamField = new TextField();
        TextField adresField = new TextField();
        NumberField postcodeField = new NumberField();
        NumberField gsmField = new NumberField();

        H3 titel1 = new H3("account informatie:");
        H3 titel2 = new H3("winkel informatie:");
        Hr hr1 = new Hr();

        emailField.setPlaceholder("E-mail");
        passwordField.setPlaceholder("Passwoord");
        naamField.setPlaceholder("Naam");
        voornaamField.setPlaceholder("Voornaam");
        adresField.setPlaceholder("Adres");
        postcodeField.setPlaceholder("Postcode");
        gsmField.setPlaceholder("Telefoon nummer");

        Button button = new Button("Sign up");


        add(new H1("Sign up"));
        add(titel1);
        add(emailField,passwordField);
        add(hr1);
        add(titel2);
        add(naamField,voornaamField,adresField,postcodeField,gsmField,button);
    }
}
