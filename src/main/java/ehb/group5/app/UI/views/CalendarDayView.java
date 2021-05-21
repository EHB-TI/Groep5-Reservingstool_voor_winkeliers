package ehb.group5.app.UI.views;

import com.mysql.cj.x.protobuf.MysqlxCursor;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.table.CompanyEntity;
import ehb.group5.app.backend.data.table.OpeningHourEntity;
import ehb.group5.app.backend.data.table.StoreEntity;

import java.awt.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Route("calendarday")
@PageTitle("Agenda per dag")
@CssImport("./styles/calendarday.css")
public class CalendarDayView extends CommonLayout {
    private static final String[] MONTHS_OF_YEAR = {"januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december"};

    private Div mainDiv;

    public CalendarDayView() {
        CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("account");

        mainDiv = new Div();
        mainDiv.addClassName("calendarday-content");

        Div titleDiv = new Div();
        titleDiv.addClassName("calendar-title");

        //button om terug te gaan naar kalender
        Button titleButton = new Button("<--");
        titleButton.addClickListener(event -> {
           UI.getCurrent().navigate(CalendarView.class);
        });

        H1 h1 = new H1();

        titleDiv.add(titleButton, h1);

        StoreEntity choosenStore = (StoreEntity) VaadinSession.getCurrent().getAttribute("calendarStore");
        LocalDate date = (LocalDate) VaadinSession.getCurrent().getAttribute("calendarDate");

        if (date == null
                || choosenStore == null) {
            UI.getCurrent().getPage().setLocation("calendar");
            return;
        }

        Calendar cal = Calendar.getInstance();
        cal.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

        //locale datum van de kalender
        h1.setText(cal.get(Calendar.DAY_OF_MONTH) + " " + MONTHS_OF_YEAR[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.YEAR));
        mainDiv.add(titleDiv);
        getContainer().add(mainDiv);

        //database "linken"
        for (StoreEntity store : company.getStores()) {
            if(!store.equals(choosenStore)){
                continue;
            }
            List<Calendar> datesPerDay = new ArrayList<>();
            store.getReservations()
                    .forEach(reservation -> {
                        Calendar reservationDate = Calendar.getInstance();
                        reservationDate.setTime(reservation.getDate());

                        if(reservationDate.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
                                && reservationDate.get(Calendar.MONTH) == cal.get(Calendar.MONTH)
                                && reservationDate.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)){
                            datesPerDay.add(reservationDate);
                        }
            });
            //database "linken"
            OpeningHourEntity openingHour = null;

            for (OpeningHourEntity hour : store.getOpeningHours()) {
                if(cal.get(Calendar.DAY_OF_WEEK) == hour.getWeekDay()){
                    openingHour = hour;
                }
            }

            if (openingHour != null) {
                long beginInMiliseconde = openingHour.getBeginHour().getTime();
                long endInMiniseconde = openingHour.getEndHour().getTime();

                long diffInMiliseconde = Math.abs(beginInMiliseconde - endInMiniseconde);
                long diff = TimeUnit.MINUTES.convert(diffInMiliseconde, TimeUnit.MILLISECONDS);

                int times = (int) Math.floor(diff / store.getMaxTime());

                Calendar currentDate = Calendar.getInstance();
                currentDate.set(cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH),
                        openingHour.getBeginHour().getHours(),
                        openingHour.getBeginHour().getMinutes());


                Calendar nextDate = Calendar.getInstance();
                nextDate.set(cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH),
                        openingHour.getBeginHour().getHours(),
                        openingHour.getBeginHour().getMinutes());
                nextDate.add(Calendar.MINUTE, store.getMaxTime());

                //de tijd op dagelijkse kalender, als het nog geen openingsuren heeft geeft het een error
                for(int i = 0; i < times; i++){
                    int amount = 0;
                    for (Calendar entry : datesPerDay) {
                        if(entry.getTime().after(currentDate.getTime())
                                && entry.getTime().before(nextDate.getTime())){
                            amount++;
                        }
                    }
                    insertHour(currentDate.get(Calendar.HOUR_OF_DAY) + ":" + (currentDate.get(Calendar.MINUTE) < 10? "0" : "") + currentDate.get(Calendar.MINUTE),
                            amount);
                    currentDate.add(Calendar.MINUTE, store.getMaxTime());
                    nextDate.add(Calendar.MINUTE, store.getMaxTime());
                }
            }
            else{
                insertError("Deze dag heeft geen openingsuur");
            }
        }
    }
    // waar er klanten zijn wordt het groen geen klanten blijft het grijs
    public void insertHour(String hour, int klanten) {
        Div div = new Div();
        if(klanten > 0)
            div.getStyle().set("background-color", "green");
        div.addClassName("calendarday");
        Span hourSpan = new Span(hour);
        Span klantenSpan = new Span(klanten + " klant");
        hourSpan.setId("uur");
        klantenSpan.setId("klanten");
        div.add(hourSpan, klantenSpan);
        mainDiv.add(div);
    }

    public void insertError(String message){
        Div div = new Div();
        div.addClassName("error");
        Span errorSpan = new Span(message);
        div.add(errorSpan);
        mainDiv.add(div);
    }
}




