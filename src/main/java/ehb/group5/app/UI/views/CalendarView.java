package ehb.group5.app.UI.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ehb.group5.app.UI.layouts.CommonLayout;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;
import org.vaadin.stefan.fullcalendar.FullCalendarBuilder;

import java.time.LocalDate;
@Route("calendar")
@PageTitle("Agenda per maand")
@CssImport("./styles/calendar.css")
public class CalendarView extends CommonLayout {
    public CalendarView() {
        H1 h1 = new H1();
        h1.setText("3 mei 2021");
        add(h1);

        // Create a new calendar instance and attach it to our layout
        FullCalendar calendar = FullCalendarBuilder.create().build();
        getContainer().add(calendar);
        setFlexGrow(1, calendar);

// Create a initial sample entry
        Entry entry = new Entry();
        entry.setTitle("Some event");
        entry.setStart(LocalDate.now().withDayOfMonth(3).atTime(10, 0), calendar.getTimezone());
        entry.setEnd(entry.getStart().plusHours(2), calendar.getTimezone());
        entry.setColor("#ff3333");

        calendar.addEntry(entry);
    }

}
