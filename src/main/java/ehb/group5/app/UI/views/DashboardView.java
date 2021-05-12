package ehb.group5.app.UI.views;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.DatabaseService;
import ehb.group5.app.backend.data.table.CompanyEntity;
import ehb.group5.app.backend.data.table.ReservationEntity;
import ehb.group5.app.backend.data.table.StoreEntity;
import ehb.group5.app.backend.utils.ChartUtils;
import io.requery.query.function.Count;
import lombok.val;

import java.awt.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


@Route("")
@PageTitle("Dashboard")
@CssImport(value = "./styles/dashboard.css")
public class DashboardView extends CommonLayout {

    private static final String[] DAYS_OF_WEEK = {"Zondag", "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag"};
    private static final String[] MONTHS_OF_YEAR = {"Januari", "Februari", "Maart", "April", "Mei", "Juni", "Juli", "Augustus", "September", "Oktober", "November", "December"};
    private CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("company");

    public DashboardView() {
        Div gridDiv = new Div();
        gridDiv.setId("dashboard_grid");

        val statDayDiv = new Div();
        statDayDiv.setId("day");
        statDayDiv.setClassName("card");
        val statDayBodyDiv = new Div();
        statDayBodyDiv.setClassName("card-body");
        statDayBodyDiv.add(getDayStats());
        statDayDiv.add(statDayBodyDiv);

        val statMonthDiv = new Div();
        statMonthDiv.setId("month");
        statMonthDiv.setClassName("card");
        val statMonthBodyDiv = new Div();
        statMonthBodyDiv.setClassName("card-body");
        statMonthBodyDiv.add(getYearStats());
        statMonthDiv.add(statMonthBodyDiv);

        gridDiv.add(statDayDiv, statMonthDiv);
        getContainer().add(gridDiv);
        add(getContainer());
    }


    public Chart getDayStats(){
        LocalDate weekAgoDate = LocalDate.now().minusDays(7);
        Calendar weekAgo = Calendar.getInstance();
        weekAgo.set(weekAgoDate.getYear(), weekAgoDate.getMonthValue(), weekAgoDate.getDayOfMonth());

        val dayChart = new ChartUtils.ChartBuilder(ChartType.AREASPLINE)
                .setTitle("Klanten laatste 7 dagen")
                .setTooltipUnit(" klanten");

        for(StoreEntity store: company.getStores()){
            Random rand = new Random();
            Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            TreeMap<Calendar, Integer> datesPerDay = new TreeMap<>(Comparator.comparing(Calendar::getTime));
            store.getReservations()
                    .forEach(reservation -> {
                        Calendar date = Calendar.getInstance();
                        date.setTime(new Date(reservation.getDate().getTime()));

                        if (date.getTime().before(new Date()) && date.getTime().before(weekAgo.getTime())) {
                            boolean found = false;
                            for (Map.Entry<Calendar, Integer> entry : datesPerDay.entrySet()) {
                                if(date.get(Calendar.YEAR) == entry.getKey().get(Calendar.YEAR)
                                        && date.get(Calendar.MONTH) == entry.getKey().get(Calendar.MONTH)
                                        && date.get(Calendar.DAY_OF_MONTH) == entry.getKey().get(Calendar.DAY_OF_MONTH)){
                                    found = true;
                                    entry.setValue(entry.getValue() + 1);
                                }
                            }

                            if(!found){
                                datesPerDay.put(date, 1);
                            }
                        }
                    });
            String[] categories = {"","","","","","",""};
            Number[] values = new Number[] {0, 0, 0, 0, 0, 0, 0};
            for(int i = 0; i < 7; i++){
                Calendar dayOfWeek = Calendar.getInstance();
                dayOfWeek.setTime(new Date());
                dayOfWeek.add(Calendar.DAY_OF_YEAR, -(6 - i));
                categories[i] = DAYS_OF_WEEK[dayOfWeek.get(Calendar.DAY_OF_WEEK) - 1] + " " + dayOfWeek.get(Calendar.DAY_OF_MONTH) + " " + MONTHS_OF_YEAR[dayOfWeek.get(Calendar.MONTH)];

                for (Map.Entry<Calendar, Integer> entry : datesPerDay.entrySet()) {
                    if(dayOfWeek.get(Calendar.YEAR) == entry.getKey().get(Calendar.YEAR)
                            && dayOfWeek.get(Calendar.MONTH) == entry.getKey().get(Calendar.MONTH)
                            && dayOfWeek.get(Calendar.DAY_OF_MONTH) == entry.getKey().get(Calendar.DAY_OF_MONTH)){
                        values[i] = values[i].intValue() + entry.getValue();
                    }
                }
            }
            dayChart.setCategories(categories);
            dayChart.getChart().getConfiguration().addSeries(new ListSeries("# Klanten voor " + store.getName(), values));
        }
        return dayChart.build();
    }

    public Chart getYearStats(){
        LocalDate nowDate = LocalDate.now();
        Calendar now = Calendar.getInstance();
        now.set(nowDate.getYear(), nowDate.getMonthValue(), nowDate.getDayOfMonth());

        val dayChart = new ChartUtils.ChartBuilder(ChartType.AREASPLINE)
                .setTitle("Klanten laatste 12 maanden")
                .setTooltipUnit(" klanten");

        for(StoreEntity store: company.getStores()){
            Random rand = new Random();
            Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            TreeMap<Calendar, Integer> datesPerDay = new TreeMap<>(Comparator.comparing(Calendar::getTime));
            store.getReservations()
                    .forEach(reservation -> {
                        Calendar date = Calendar.getInstance();
                        date.setTime(new Date(reservation.getDate().getTime()));

                        if (date.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
                            boolean found = false;
                            for (Map.Entry<Calendar, Integer> entry : datesPerDay.entrySet()) {
                                if(date.get(Calendar.YEAR) == entry.getKey().get(Calendar.YEAR)
                                        && date.get(Calendar.MONTH) == entry.getKey().get(Calendar.MONTH)){
                                    found = true;
                                    entry.setValue(entry.getValue() + 1);
                                }
                            }

                            if(!found){
                                datesPerDay.put(date, 1);
                            }
                        }
                    });
            String[] categories = {"","","","","","","","","","","",""};
            Number[] values = new Number[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for(int i = 0; i < 12; i++){
                Calendar monthOfYear = Calendar.getInstance();
                monthOfYear.setTime(new Date());
                monthOfYear.add(Calendar.MONTH, -(11 - i));
                categories[i] = MONTHS_OF_YEAR[monthOfYear.get(Calendar.MONTH)] + " " + (monthOfYear.get(Calendar.YEAR) - 2000);

                for (Map.Entry<Calendar, Integer> entry : datesPerDay.entrySet()) {
                    if(monthOfYear.get(Calendar.YEAR) == entry.getKey().get(Calendar.YEAR)
                            && monthOfYear.get(Calendar.MONTH) == entry.getKey().get(Calendar.MONTH)){
                        values[i] = values[i].intValue() + entry.getValue();
                    }
                }
            }
            dayChart.setCategories(categories);
            dayChart.getChart().getConfiguration().addSeries(new ListSeries("# Klanten voor " + store.getName(), values));
        }
        return dayChart.build();
    }
}

