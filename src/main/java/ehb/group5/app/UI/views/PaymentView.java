package ehb.group5.app.UI.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.layouts.CommonLayout;

@Route("payment")
@PageTitle("payment")
@CssImport("./styles/calendar.css")

public class PaymentView extends CommonLayout {

    public PaymentView(){

        add(new H1(VaadinSession.getCurrent().getAttribute("parameter").toString()));
    }
}
