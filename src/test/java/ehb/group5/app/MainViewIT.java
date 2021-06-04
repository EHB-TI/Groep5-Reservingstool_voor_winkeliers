package ehb.group5.app;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.InternalServerError;
import com.vaadin.flow.router.RouteNotFoundError;
import ehb.group5.app.UI.views.*;
import ehb.group5.app.backend.data.table.AdminEntity;
import ehb.group5.app.backend.data.table.CompanyEntity;
import ehb.group5.app.backend.security.PasswordAuthentication;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

public class MainViewIT {

    @Test
    public void enryptPassword() {
        String password = "mysecretpassword";
        String hasedPassword = new PasswordAuthentication().hash(password.toCharArray());
        Assert.assertFalse(password == hasedPassword);
    }

    @Test
    public void enryptPasswordCheck() {
        String password = "mysecretpassword";
        String hasedPassword = new PasswordAuthentication().hash(password.toCharArray());
        Assert.assertTrue(new PasswordAuthentication().authenticate(password.toCharArray(), hasedPassword));
    }

    @Test
    public void testDatesBefore() throws ParseException {
        Calendar calStart = Calendar.getInstance();
        Calendar calWeekAgo = Calendar.getInstance();
        Calendar checkFunction = Calendar.getInstance();

        LocalDate dateStart = LocalDate.of(2021, 1, 8);
        calStart.set(dateStart.getYear(), dateStart.getMonthValue(), dateStart.getDayOfMonth());
        LocalDate dateWeekAgo = LocalDate.of(2021, 1, 1);
        calWeekAgo.set(dateWeekAgo.getYear(), dateWeekAgo.getMonthValue(), dateWeekAgo.getDayOfMonth());

        LocalDate weekAgoDate = dateStart.minusDays(7);
        checkFunction.set(weekAgoDate.getYear(), weekAgoDate.getMonthValue(), weekAgoDate.getDayOfMonth());

        Assert.assertTrue(calWeekAgo.get(Calendar.YEAR) == checkFunction.get(Calendar.YEAR)
                && calWeekAgo.get(Calendar.MONTH) == checkFunction.get(Calendar.MONTH)
                && calWeekAgo.get(Calendar.DAY_OF_MONTH) == checkFunction.get(Calendar.DAY_OF_MONTH));
    }

    private final static Map<Class<?>, List<Class<? extends com.vaadin.flow.component.Component>>> ACCES_TO_VIEW = new HashMap<>();

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

    @Test
    public void uiServiceListenerLogicTest(){
        Class<?> navigationTarget = PaymentView.class;
        Object loggedUser = new AdminEntity();

        Class<?> resultPage = navigationTarget;

        if (!navigationTarget.equals(LoginView.class)
                && !navigationTarget.equals(SignInView.class)
                && !navigationTarget.equals(LogoutView.class)) {
            // Not logged in -> forward to login page
            if (loggedUser == null) {
                resultPage = LoginView.class;
                return;
            }

            // if page does not exist -> forward not found page
            if (navigationTarget.equals(RouteNotFoundError.class)) {
                resultPage = NotFoundView.class;
                return;
            }

            // If there is an error on the page -> do nothing
            if(navigationTarget.equals(InternalServerError.class))
                return;

            // No subscription -> forward to chooseplan page
            if (loggedUser instanceof CompanyEntity) {
                CompanyEntity company = (CompanyEntity) loggedUser;
                if (company.getSubscriptionExpiresDate() == null
                        && !navigationTarget.equals(ChoosePlanView.class)
                        && !navigationTarget.equals(PaymentView.class)) {
                    resultPage = ChoosePlanView.class;
                    return;
                }
            }

            // Check acces for the logged user type
            if(!navigationTarget.equals(NotFoundView.class)
                    && !navigationTarget.equals(ForbiddenView.class))
                for (Map.Entry<Class<?>, List<Class<? extends Component>>> entry : ACCES_TO_VIEW.entrySet()) {
                    if (loggedUser.getClass().equals(entry.getKey())
                            && !entry.getValue().contains(navigationTarget))
                        resultPage = ForbiddenView.class;
                }
        }
        Assert.assertTrue(resultPage == ForbiddenView.class);
    }



    private static final String[] DAYS_OF_WEEK = {"Zondag", "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag"};
    @Test
    public void previous7daysWithToday(){
        for (int i = 0; i < 7; i++) {
            Calendar dayOfWeek = Calendar.getInstance();
            dayOfWeek.setTime(new Date());
            dayOfWeek.add(Calendar.DAY_OF_YEAR, -(6 - i));
            System.out.print(DAYS_OF_WEEK[dayOfWeek.get(Calendar.DAY_OF_WEEK) - 1] + (i != 6? ", " : " (Today)"));
        }
    }
}
