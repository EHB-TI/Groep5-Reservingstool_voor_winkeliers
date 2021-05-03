package ehb.group5.app.UI.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;



@Route("")

public class SupportView extends VerticalLayout {

    public SupportView(){

        Button button = new Button("click me");
        add(button);
    }



}
