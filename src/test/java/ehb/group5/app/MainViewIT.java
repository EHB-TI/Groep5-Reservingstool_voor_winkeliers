package ehb.group5.app;

import ehb.group5.app.backend.security.PasswordAuthentication;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;

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
        String hasedPassword1 = new PasswordAuthentication().hash(password.toCharArray());
        String hasedPassword2 = new PasswordAuthentication().hash(password.toCharArray());
        Assert.assertTrue(hasedPassword1 == hasedPassword2);
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
}
