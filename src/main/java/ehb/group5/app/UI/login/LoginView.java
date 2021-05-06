package ehb.group5.app.UI.login;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.backend.data.table.Company;
import lombok.val;

@Route("login")
@PageTitle("Aanmelden als winkelier")
@CssImport("./styles/style.css")
public class LoginView extends VerticalLayout {


    public LoginView() {
        // Creating id & password fields
        NumberField idField = new NumberField();
        TextField passwordField = new TextField();

        // Placing a placeholder to the fields
        idField.setPlaceholder("Id");
        passwordField.setPlaceholder("Passwoord");

        // Creating button
        Button button = new Button("Inloggen");

        // Listen to button actions
        button.addClickListener(event -> {
            // Get the company by id
            val company = Company.getCompanyById((int)((double)idField.getValue()));

            // Checking password
            if(company != null && passwordField.getValue().equals(company.getPassword())){
                // Route to dashboard view
                UI.getCurrent().getPage().setLocation("");

                // Saving company to the current session
                VaadinSession.getCurrent().setAttribute("company", company);
            }
            else{
                // TODO Fout of bestaat niet
            }
        });
    }

}
