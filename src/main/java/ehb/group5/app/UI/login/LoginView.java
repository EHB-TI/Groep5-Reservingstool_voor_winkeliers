package ehb.group5.app.UI.login;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Aanmelden als winkelier")
@CssImport("./styles/style.css")
public class LoginView extends VerticalLayout {


    public LoginView() {
        // Use TextField for standard text input
       add(new H1("Hello world"));

       Button button = new Button("click me", click -> {
           add(new Span("you clicked"));
       });
    }

}
