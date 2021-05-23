package ehb.group5.app.UI.views;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import ehb.group5.app.UI.layouts.CommonLayout;
import lombok.val;

@Route("404")
@PageTitle("Page not found")
public class NotFoundView extends CommonLayout {

    public NotFoundView() {
        getContainer().getStyle().set("display", "flex");
        getContainer().getStyle().set("justify-content", "center");

        val div = new Div();
        div.getStyle().set("display", "flex");
        div.getStyle().set("justify-content", "center");
        div.getStyle().set("flex-direction", "column");
        div.getStyle().set("text-align", "center");

        val title = new H1("404");
        val subTitle = new H3("Page not found");
        val dashboardAnchor = new RouterLink("Ga naar de dashboard ->", DashboardView.class);

        div.add(title, subTitle, dashboardAnchor);
        getContainer().add(div);
    }
}
