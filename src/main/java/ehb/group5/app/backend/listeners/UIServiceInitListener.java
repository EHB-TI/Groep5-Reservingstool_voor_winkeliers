package ehb.group5.app.backend.listeners;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.views.LoginView;
import org.springframework.stereotype.Component;

/**
 * Source: https://vaadin.com/docs/v14/flow/tutorials/in-depth-course/login-and-authentication
 * @author Vaadin
 */
@Component
public class UIServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    private void authenticateNavigation(BeforeEnterEvent event) {
        if (!LoginView.class.equals(event.getNavigationTarget())
                && VaadinSession.getCurrent().getAttribute("company") == null) {
            event.forwardTo(LoginView.class);
        }
    }
}
