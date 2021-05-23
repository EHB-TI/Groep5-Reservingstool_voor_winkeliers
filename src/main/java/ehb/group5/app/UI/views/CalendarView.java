package ehb.group5.app.UI.views;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.table.CompanyEntity;
import ehb.group5.app.backend.data.table.StoreEntity;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;
import org.vaadin.stefan.fullcalendar.FullCalendarBuilder;

import java.awt.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Route("calendar")
@PageTitle("Agenda per maand")
@CssImport("./styles/calendar.css")
public class CalendarView extends CommonLayout {
    private static final String[] MONTHS_OF_YEAR = {"januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december"};

    private final Map<Entry, StoreEntity> entryStoreEntityMap = new HashMap<>();

    public CalendarView() {
        getContainer().setClassName("calendar_page");

        H1 h1 = new H1();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());



        h1.setText(cal.get(Calendar.DAY_OF_MONTH) + " " + MONTHS_OF_YEAR[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.YEAR));
        getContainer().add(h1);
        // Create a new calendar instance and attach it to our layout
        FullCalendar calendar = FullCalendarBuilder.create().build();
        getContainer().add(calendar);

        //legende onderaan kalender
        Div legendDiv = new Div();
        legendDiv.addClassName("calendar-legend");
        getContainer().add(legendDiv);

        //events
        calendar.addEntryClickedListener(event -> {
           if(entryStoreEntityMap.get(event.getEntry()) != null){
               LocalDateTime ldt = event.getEntry().getStart();
               VaadinSession.getCurrent().setAttribute("calendarStore", entryStoreEntityMap.get(event.getEntry()));
               VaadinSession.getCurrent().setAttribute("calendarDate", LocalDate.of(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth()));
               UI.getCurrent().navigate(CalendarDayView.class);
           }
        });


        /*calendar.addDayNumberClickedListener(event -> {
            if(event.getSource().getEntries(event.getDate()).size() > 0) {
                VaadinSession.getCurrent().setAttribute("calendarDate", event.getDate());
                UI.getCurrent().getPage().setLocation("calendarday");
            }
        });*/


        //kalender zelf
        CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("account");
        for (StoreEntity store : company.getStores()) {
            Random rand = new Random(store.getId());
            Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            Map<Calendar, Integer> datesPerDay = new HashMap<>();
            store.getReservations()
                    .forEach(reservation -> {
                        Calendar date = Calendar.getInstance();
                        date.setTime(reservation.getDate());
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
                    });
            for (Map.Entry<Calendar, Integer> entry : datesPerDay.entrySet()) {
                Entry calendarEntry = new Entry();
                LocalDate ld = LocalDate.of(entry.getKey().get(Calendar.YEAR),
                        entry.getKey().get(Calendar.MONTH) + 1,
                        entry.getKey().get(Calendar.DAY_OF_MONTH));
                calendarEntry.setTitle(entry.getValue() + " klanten");
                calendarEntry.setAllDay(true);
                calendarEntry.setStart(LocalDateTime.of(ld, LocalTime.of(9, 0)));
                calendarEntry.setEnd(calendarEntry.getStart().plusHours(2), calendar.getTimezone());
                calendarEntry.setColor(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
                calendar.addEntry(calendarEntry);

                entryStoreEntityMap.put(calendarEntry, store);
            }

            //legende styling
            Span legendItemSpan = new Span(store.getName());
            legendItemSpan.addClassName("calendar-legend-item");
            legendItemSpan.getStyle().set("background-color", String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
            legendDiv.add(legendItemSpan);
        }
    }
}










