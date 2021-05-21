package ehb.group5.app.UI.views;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.DatabaseService;
import ehb.group5.app.backend.data.table.CompanyEntity;
import ehb.group5.app.backend.data.table.OpeningHourEntity;
import ehb.group5.app.backend.data.table.SpecialClosureEntity;
import ehb.group5.app.backend.data.table.StoreEntity;
import io.requery.sql.RowCountException;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;

/*
     Author: ZOETARDT Craig (backend), FAILLE Arnaud (frontend)
     email: craig.zoetardt@student.ehb.be , arnaud.faille@student.ehb.be
     */

@Route("edit")
@PageTitle("Profiel")
@CssImport("./styles/edit.css")
public class EditView extends CommonLayout {

    private static final String[] DAYS_OF_WEEK = {"zondag", "maandag", "dinsdag", "woensdag", "donderdag", "vrijdag", "zaterdag"};
    private Div leftDiv;
    private Div rightDiv;

    private ArrayList<OpeningHourEntity> openingHours = new ArrayList<>();
    private ArrayList<SpecialClosureEntity> specialClosures = new ArrayList<>();

    public EditView() {
        CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("account");
        StoreEntity store = company.getStores().firstOrNull();

        Div div = new Div();
        div.addClassNames("edit-content");

        /**
         * Top DIV
         * */
        Div topDiv = new Div();
        topDiv.addClassNames("edit-content-top");

        topDiv.add(new H1("Edit"));

        Image image = new Image("https://dummyimage.com/400x400/000/fff", "DummyImage");
        Button button = new Button("Afbeelding uploaden");

        TextArea descriptionField = new TextArea("Omschrijving");
        descriptionField.setPlaceholder("Schrijf hier");

        topDiv.add(image, button, descriptionField);


        /**
         * Left DIV
         * */
        leftDiv = new Div();
        leftDiv.addClassNames("edit-content-left");
        Div adressdiv = new Div();
        adressdiv.setId("adrespostdiv");

        TextField adressField = new TextField("Adres");
        TextField postbusField = new TextField("Postcode");

        adressField.setWidth("74%");
        postbusField.setWidth("25%");

        Button addOpeningHourButton = new Button("Openingsuur toevoegen", event -> {
            leftDiv.add(createNewOpeningClosures(store, null));
        });
        adressdiv.add(adressField, postbusField);
        leftDiv.add(adressdiv, addOpeningHourButton);

        /**
         * Right DIV
         * */
        rightDiv = new Div();
        rightDiv.addClassNames("edit-content-right");

        TextField phoneField = new TextField("Telefoonnummer");

        Button addbutton = new Button("Uitzonderlijke sluiting toevoegen", event -> {
            rightDiv.add(createNewSpecialClosures(store, null));
        });

        phoneField.setMaxLength(14);
        phoneField.setMinLength(11);
        phoneField.setErrorMessage("Een Belgisch gsm nummer telt 11 nummers of 14 als je +32 gebruikt.");

        rightDiv.add(phoneField, addbutton);

        /**
         * BOttom div
         */

        Div bottomDib = new Div();
        bottomDib.addClassNames("edit-content-bottom");

        Button saveButton = new Button("Save Changes", event -> {
            for (OpeningHourEntity entry : openingHours) {
                try {
                    DatabaseService.getOpeningHoursStore().upsert(entry);
                } catch (RowCountException e) {

                }
            }
            for (SpecialClosureEntity entry : specialClosures) {
                try {
                    DatabaseService.getSpecialClosuresStore().upsert(entry);
                } catch (RowCountException e) {

                }
            }

            store.setDescription(descriptionField.getValue());
            store.setPhoneNumber(phoneField.getValue());
            store.setAdress(adressField.getValue());

            try {
                DatabaseService.getStoresStore().update(store);
            } catch (RowCountException e) {

            }
            UI.getCurrent().navigate(DashboardView.class);
        });

        bottomDib.add(saveButton);


        div.add(topDiv, leftDiv, rightDiv, bottomDib);
        getContainer().add(div);


        for (OpeningHourEntity openingHour : store.getOpeningHours()) {
            leftDiv.add(createNewOpeningClosures(store, openingHour));
        }

        for (SpecialClosureEntity specialClosure : store.getSpecialClosures()) {
            rightDiv.add(createNewSpecialClosures(store, specialClosure));
        }

        descriptionField.setValue(store.getDescription() != null? store.getDescription() : "");
        descriptionField.setClearButtonVisible(true);
        adressField.setValue(store.getAdress());
        adressField.setClearButtonVisible(true);
        phoneField.setValue(store.getPhoneNumber());
        phoneField.setClearButtonVisible(true);
        postbusField.setValue(store.getPostbus());
        postbusField.setClearButtonVisible(true);


    }

    private Div createNewSpecialClosures(StoreEntity store, SpecialClosureEntity entity) {
        final SpecialClosureEntity finalEntity;
        if (entity == null)
            finalEntity = new SpecialClosureEntity();
        else
            finalEntity = entity;

        Div div = new Div();
        div.addClassNames("card", "card-body", "mt-2");

        Icon closeIcon = new Icon(VaadinIcon.CLOSE_SMALL);
        closeIcon.addClassNames("close");
        closeIcon.addClickListener(event -> {
            rightDiv.remove(div);
            specialClosures.remove(finalEntity);
            DatabaseService.getSpecialClosuresStore().delete(finalEntity);
        });

        DatePicker specialClosuresDatePicker = new DatePicker();
        specialClosuresDatePicker.setLabel("Speciale sluiting");
        if (finalEntity.getDate() != null)
            specialClosuresDatePicker.setValue(finalEntity.getDate().toLocalDate());
        specialClosuresDatePicker.addValueChangeListener(event -> {
            finalEntity.setDate(Date.valueOf(specialClosuresDatePicker.getValue()));
        });

        TextArea specialClosuresReasonField = new TextArea("Reden");
        if (finalEntity.getReason() != null)
            specialClosuresReasonField.setValue(finalEntity.getReason());
        specialClosuresReasonField.addValueChangeListener(event -> {
            finalEntity.setReason(specialClosuresReasonField.getValue());
        });

        finalEntity.setStore(store);

        specialClosures.add(finalEntity);

        div.add(closeIcon, specialClosuresDatePicker, specialClosuresReasonField);
        return div;
    }

    private Div createNewOpeningClosures(StoreEntity store, OpeningHourEntity entity) {
        final OpeningHourEntity finalEntity;
        if (entity == null)
            finalEntity = new OpeningHourEntity();
        else
            finalEntity = entity;

        Div div = new Div();
        div.addClassNames("card", "card-body", "mt-2");

        Icon closeIcon = new Icon(VaadinIcon.CLOSE_SMALL);
        closeIcon.addClassNames("close");
        closeIcon.addClickListener(event -> {
            leftDiv.remove(div);
            openingHours.remove(finalEntity);
            DatabaseService.getOpeningHoursStore().delete(finalEntity);
        });

        Select<String> openingHourSelect = new Select<>();
        openingHourSelect.setItems(DAYS_OF_WEEK);
        openingHourSelect.setLabel("Open dagen");
        openingHourSelect.setValue(DAYS_OF_WEEK[finalEntity.getWeekDay()]);
        openingHourSelect.addValueChangeListener(event -> {
            for (int i = 0; i < DAYS_OF_WEEK.length; i++) {
                if (DAYS_OF_WEEK[i].equals(openingHourSelect.getValue())) {
                    finalEntity.setWeekDay(i);
                }
            }
        });


        TimePicker openingHourStartTimePicker = new TimePicker();
        openingHourStartTimePicker.setLabel("Openingsuren");
        if (finalEntity.getBeginHour() != null)
            openingHourStartTimePicker.setValue(finalEntity.getBeginHour().toLocalTime());
        openingHourStartTimePicker.setStep(Duration.ofMinutes(store.getMaxTime()));
        openingHourStartTimePicker.addValueChangeListener(event -> {
            finalEntity.setBeginHour(Time.valueOf(openingHourStartTimePicker.getValue()));
        });

        TimePicker openingHourEndTimePicker = new TimePicker();
        openingHourEndTimePicker.setLabel("Sluitingsuren");
        openingHourEndTimePicker.addClassNames("timepicker");
        if (finalEntity.getEndHour() != null)
            openingHourEndTimePicker.setValue(finalEntity.getEndHour().toLocalTime());
        openingHourEndTimePicker.setStep(Duration.ofMinutes(store.getMaxTime()));
        openingHourEndTimePicker.addValueChangeListener(event -> {
            finalEntity.setEndHour(Time.valueOf(openingHourEndTimePicker.getValue()));
        });

        finalEntity.setStore(store);

        openingHours.add(finalEntity);

        div.add(closeIcon, openingHourSelect, openingHourStartTimePicker, openingHourEndTimePicker);
        return div;
    }

}