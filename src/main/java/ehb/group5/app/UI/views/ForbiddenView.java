package ehb.group5.app.UI.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import ehb.group5.app.UI.layouts.CommonLayout;
import lombok.val;

/**
 * @author Arnaud Faille
 */
@Route("403")
@PageTitle("Page not found")
public class ForbiddenView extends CommonLayout {

    public ForbiddenView(){
        getContainer().getStyle().set("display", "flex");
        getContainer().getStyle().set("justify-content", "center");

        val div = new Div();
        div.getStyle().set("display", "flex");
        div.getStyle().set("justify-content", "center");
        div.getStyle().set("flex-direction", "column");
        div.getStyle().set("text-align", "center");

        val title = new H1("403");
        val subTitle = new H3("Access forbidden");

        div.add(title, subTitle);
        getContainer().add(div);
    }
}
