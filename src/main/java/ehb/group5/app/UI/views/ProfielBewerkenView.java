package ehb.group5.app.UI.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.DatabaseService;
import ehb.group5.app.backend.data.table.CompanyEntity;
import lombok.Getter;

@Route("profiel/edit")
@PageTitle("ProfielBewerken")
@CssImport("./styles/ProfielBewerken.css")

public class ProfielBewerkenView extends CommonLayout {

     /*
     Author: Zakaria Lamsakam
     email: zakaria.lamsakam@student.ehb.be
     */

    public ProfielBewerkenView(){

        CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("account");

        Div Contentdiv = new Div();
        Contentdiv.setId("contentid");

        //Titel aanmaken
        Contentdiv.add(new H1("Profiel bewerken"));


        //Email initialiseren
        EmailField emailField = new EmailField("Nieuwe Email");
        emailField.setValue(company.getEmail());
        emailField.setClearButtonVisible(true);
        emailField.setErrorMessage("Please enter a valid email address");

        //De maximum limiet van letters implementeren
        emailField.setMaxLength(30);

        //Password initialiseren
        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Nieuwe Wachtwoord");
        passwordField.setClearButtonVisible(true);
        passwordField.setErrorMessage("Uw wachtwoord moet minstens 6 characters bevatten");
        passwordField.setMaxLength(50);
        passwordField.setMinLength(6);


        //knop aanmaken
        Button button = new Button("Save");
        button.addClickShortcut(Key.ENTER);
        button.addClickListener(buttonClickEvent ->{
            if (emailField.getValue()!= null
                    && passwordField.getValue() != null
                    && !emailField.getValue().isEmpty()
                    && !passwordField.getValue().isEmpty()
                    && !emailField.isInvalid()
                    && !passwordField.isInvalid()
            ){

            company.setEmail(emailField.getValue());
            company.setPassword(passwordField.getValue());
            DatabaseService.getCompaniesStore().update(company);
            UI.getCurrent().getPage().setLocation("profiel");
            } else {
                Notification.show("Alles is niet correct ingevuld.");
            }

        });

        Contentdiv.add(emailField);
        Contentdiv.add(passwordField);
        Contentdiv.add(button);
        getContainer().add(Contentdiv);


    }

}
