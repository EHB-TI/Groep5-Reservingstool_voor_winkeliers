package ehb.group5.app.UI.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ehb.group5.app.UI.layouts.CommonLayout;

@Route("views")
@PageTitle("payment")
@CssImport("./styles/calendar.css")

public class PaymentView extends CommonLayout {

    public PaymentView(){
        add(new H1("paymenbt"));
    }
}
