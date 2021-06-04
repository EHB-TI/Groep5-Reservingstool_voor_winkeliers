package ehb.group5.app.UI.views;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.table.CompanyEntity;
import ehb.group5.app.backend.data.table.StoreEntity;
import ehb.group5.app.backend.utils.ChartUtils;
import lombok.val;

import java.awt.*;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Arnaud Faille
 */
@Route("")
@PageTitle("Dashboard")
@CssImport(value = "./styles/dashboard.css")
public class DashboardView extends CommonLayout {

    private static final String[] DAYS_OF_WEEK = {"Zondag", "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag"};
    private static final String[] MONTHS_OF_YEAR = {"Januari", "Februari", "Maart", "April", "Mei", "Juni", "Juli", "Augustus", "September", "Oktober", "November", "December"};
    private CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("account");

    // Creating dashboard view
    public DashboardView() {
        Div gridDiv = new Div();
        gridDiv.setId("dashboard_grid");

        // Div with last 7 days chart
        val statDayDiv = new Div();
        statDayDiv.setId("day");
        statDayDiv.setClassName("card");
        val statDayBodyDiv = new Div();
        statDayBodyDiv.setClassName("card-body");
        statDayBodyDiv.add(getDayStats());
        statDayDiv.add(statDayBodyDiv);

        // Div with last 12 month chart
        val statMonthDiv = new Div();
        statMonthDiv.setId("month");
        statMonthDiv.setClassName("card");
        val statMonthBodyDiv = new Div();
        statMonthBodyDiv.setClassName("card-body");
        statMonthBodyDiv.add(getYearStats());
        statMonthDiv.add(statMonthBodyDiv);

        // Adding elements to the page
        gridDiv.add(statDayDiv, statMonthDiv);
        getContainer().add(gridDiv);
    }


    /**
     * Creates chart of all reservations of last 7 days
     * Code of dates inspired from https://www.geeksforgeeks.org/calendar-class-in-java-with-examples/
     * @return Return the created chart
     */
    public Chart getDayStats() {
        // Get out-and-outer dates
        LocalDate weekAgoDate = LocalDate.now().minusDays(7);
        Calendar weekAgo = Calendar.getInstance();
        weekAgo.set(weekAgoDate.getYear(), weekAgoDate.getMonthValue(), weekAgoDate.getDayOfMonth());

        // Creates chart with chartbuilder
        val dayChart = new ChartUtils.ChartBuilder(ChartType.AREASPLINE)
                .setTitle("Klanten laatste 7 dagen")
                .setTooltipUnit(" klanten");

        for (StoreEntity store : company.getStores()) {
            // Creates a color for each store
            Random rand = new Random();
            Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());

            TreeMap<Calendar, Integer> datesPerDay = new TreeMap<>(Comparator.comparing(Calendar::getTime));
            store.getReservations()
                    .forEach(reservation -> {
                        Calendar date = Calendar.getInstance();
                        date.setTime(new Date(reservation.getDate().getTime()));

                        // Check if the day already exist in the TreeMap to regroup reservations per day
                        if (date.getTime().before(new Date()) && date.getTime().before(weekAgo.getTime())) {
                            boolean found = false;
                            for (Map.Entry<Calendar, Integer> entry : datesPerDay.entrySet()) {
                                if (date.get(Calendar.YEAR) == entry.getKey().get(Calendar.YEAR)
                                        && date.get(Calendar.MONTH) == entry.getKey().get(Calendar.MONTH)
                                        && date.get(Calendar.DAY_OF_MONTH) == entry.getKey().get(Calendar.DAY_OF_MONTH)) {
                                    found = true;
                                    entry.setValue(entry.getValue() + 1);
                                }
                            }

                            if (!found) {
                                datesPerDay.put(date, 1);
                            }
                        }
                    });
            String[] categories = {"", "", "", "", "", "", ""};
            Number[] values = new Number[]{0, 0, 0, 0, 0, 0, 0};
            // Looping all 7 days before the date of now
            for (int i = 0; i < 7; i++) {
                Calendar dayOfWeek = Calendar.getInstance();
                dayOfWeek.setTime(new Date());
                dayOfWeek.add(Calendar.DAY_OF_YEAR, -(6 - i));
                categories[i] = DAYS_OF_WEEK[dayOfWeek.get(Calendar.DAY_OF_WEEK) - 1] + " " + dayOfWeek.get(Calendar.DAY_OF_MONTH) + " " + MONTHS_OF_YEAR[dayOfWeek.get(Calendar.MONTH)];

                for (Map.Entry<Calendar, Integer> entry : datesPerDay.entrySet()) {
                    if (dayOfWeek.get(Calendar.YEAR) == entry.getKey().get(Calendar.YEAR)
                            && dayOfWeek.get(Calendar.MONTH) == entry.getKey().get(Calendar.MONTH)
                            && dayOfWeek.get(Calendar.DAY_OF_MONTH) == entry.getKey().get(Calendar.DAY_OF_MONTH)) {
                        values[i] = values[i].intValue() + entry.getValue();
                    }
                }
            }
            // Adding categories and values to the chart
            dayChart.setCategories(categories);
            dayChart.getChart().getConfiguration().addSeries(new ListSeries("# Klanten voor " + store.getName(), values));
        }
        return dayChart.build();
    }

    /**
     * Creates chart of all reservations of last 12 month
     * Code of dates inspired from https://www.geeksforgeeks.org/calendar-class-in-java-with-examples/
     * @return Return the created chart
     */
    public Chart getYearStats() {
        LocalDate nowDate = LocalDate.now();
        Calendar now = Calendar.getInstance();
        now.set(nowDate.getYear(), nowDate.getMonthValue(), nowDate.getDayOfMonth());

        // Creates chart with chartbuilder
        val dayChart = new ChartUtils.ChartBuilder(ChartType.AREASPLINE)
                .setTitle("Klanten laatste 12 maanden")
                .setTooltipUnit(" klanten");

        for (StoreEntity store : company.getStores()) {
            // Creates a color for each store
            Random rand = new Random();
            Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            TreeMap<Calendar, Integer> datesPerDay = new TreeMap<>(Comparator.comparing(Calendar::getTime));
            store.getReservations()
                    .forEach(reservation -> {
                        Calendar date = Calendar.getInstance();
                        date.setTime(new Date(reservation.getDate().getTime()));

                        // Check if the month is already in the TreeMap to regroup it per month
                        if (date.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
                            boolean found = false;
                            for (Map.Entry<Calendar, Integer> entry : datesPerDay.entrySet()) {
                                if (date.get(Calendar.YEAR) == entry.getKey().get(Calendar.YEAR)
                                        && date.get(Calendar.MONTH) == entry.getKey().get(Calendar.MONTH)) {
                                    found = true;
                                    entry.setValue(entry.getValue() + 1);
                                }
                            }

                            if (!found) {
                                datesPerDay.put(date, 1);
                            }
                        }
                    });
            String[] categories = {"", "", "", "", "", "", "", "", "", "", "", ""};
            Number[] values = new Number[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            // Looping all months before the date of now
            for (int i = 0; i < 12; i++) {
                Calendar monthOfYear = Calendar.getInstance();
                monthOfYear.setTime(new Date());
                monthOfYear.add(Calendar.MONTH, -(11 - i));
                categories[i] = MONTHS_OF_YEAR[monthOfYear.get(Calendar.MONTH)] + " " + (monthOfYear.get(Calendar.YEAR) - 2000);

                for (Map.Entry<Calendar, Integer> entry : datesPerDay.entrySet()) {
                    if (monthOfYear.get(Calendar.YEAR) == entry.getKey().get(Calendar.YEAR)
                            && monthOfYear.get(Calendar.MONTH) == entry.getKey().get(Calendar.MONTH)) {
                        values[i] = values[i].intValue() + entry.getValue();
                    }
                }
            }
            // Adding categories and values to the chart
            dayChart.setCategories(categories);
            dayChart.getChart().getConfiguration().addSeries(new ListSeries("# Klanten voor " + store.getName(), values));
        }
        return dayChart.build();
    }
}

