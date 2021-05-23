package ehb.group5.app.backend.listeners;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.InternalServerError;
import com.vaadin.flow.router.RouteNotFoundError;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.views.*;
import ehb.group5.app.backend.data.table.AdminEntity;
import ehb.group5.app.backend.data.table.CompanyEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UIServiceInitListener implements VaadinServiceInitListener {

    private final static Map<Class<?>, List<Class<? extends com.vaadin.flow.component.Component>>> ACCES_TO_VIEW = new HashMap<>();

    public static boolean hasAcces(Class<?> clazz, Class<? extends com.vaadin.flow.component.Component> page){
        return ACCES_TO_VIEW.get(clazz) != null? ACCES_TO_VIEW.get(clazz).contains(page): false;
    }

    /**
     * Defining which type of user can acces to which page
     */
    static {
        ACCES_TO_VIEW.put(AdminEntity.class, Arrays.asList(SupportAdminView.class));
        ACCES_TO_VIEW.put(CompanyEntity.class, Arrays.asList(CalendarView.class,
                CalendarDayView.class,
                ChoosePlanView.class,
                DashboardView.class,
                EditView.class,
                PaymentView.class,
                ProfielBewerkenView.class,
                ProfielView.class,
                SignInView.class,
                SupportAdminView.class,
                SupportView.class,
                TicketView.class));
    }

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    private void authenticateNavigation(BeforeEnterEvent event) {
        if (!event.getNavigationTarget().equals(LoginView.class)
                && !event.getNavigationTarget().equals(SignInView.class)
                && !event.getNavigationTarget().equals(LogoutView.class)) {
            // Not logged in -> forward to login page
            if (VaadinSession.getCurrent().getAttribute("account") == null) {
                event.forwardTo(LoginView.class);
                return;
            }

            // if page does not exist -> forward not found page
            if (event.getNavigationTarget().equals(RouteNotFoundError.class)) {
                event.rerouteTo(NotFoundView.class);
                return;
            }

            if(event.getNavigationTarget().equals(InternalServerError.class))
                return;

            // No subscription -> forward to chooseplan page
            if (VaadinSession.getCurrent().getAttribute("account") instanceof CompanyEntity) {
                CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("account");
                if (company.getSubscriptionExpiresDate() == null
                        && !event.getNavigationTarget().equals(ChoosePlanView.class)
                        && !event.getNavigationTarget().equals(PaymentView.class)) {
                    event.forwardTo(ChoosePlanView.class);
                    return;
                }
            }

            // Check acces for the logged user type
            if(!event.getNavigationTarget().equals(NotFoundView.class)
                && !event.getNavigationTarget().equals(ForbiddenView.class))
                for (Map.Entry<Class<?>, List<Class<? extends com.vaadin.flow.component.Component>>> entry : ACCES_TO_VIEW.entrySet()) {
                    if (VaadinSession.getCurrent().getAttribute("account").getClass().equals(entry.getKey())
                            && !entry.getValue().contains(event.getNavigationTarget()))
                        event.rerouteTo(ForbiddenView.class);
            }
        }
    }
}
