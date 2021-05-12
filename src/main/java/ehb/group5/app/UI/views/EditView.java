package ehb.group5.app.UI.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.table.CompanyEntity;
import com.vaadin.flow.component.html.H1;

/* Gemaakt door ZOETARDT Craig (backend) en FAILLE Arnaud (frontend) */

@Route("edit")
@PageTitle("Profiel")
@CssImport("./styles/edit.css")
public class EditView extends CommonLayout {

    public EditView(){
        CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("company");

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
        Div leftDiv = new Div();
        leftDiv.addClassNames("edit-content-left");

        TextField adressField = new TextField("Adres");

        leftDiv.add(adressField, createNewOpeningClosures());

        /**
         * Right DIV
         * */
        Div rightDiv = new Div();
        rightDiv.addClassNames("edit-content-right");

        TextField phoneField = new TextField("Telefoonnummer");

        Button saveButton = new Button("Save Changes");

        rightDiv.add(phoneField, createNewSpecialClosures(), saveButton);


        
        div.add(topDiv, leftDiv, rightDiv);
        getContainer().add(div);
    }

    private Div createNewSpecialClosures(){
        Div div = new Div();
        div.addClassNames("card", "card-body", "mt-2");

        Icon closeIcon = new Icon(VaadinIcon.CLOSE_SMALL);
        closeIcon.addClassNames("close");

        DatePicker specialClosuresDatePicker = new DatePicker();
        specialClosuresDatePicker.setLabel("Speciale sluiting");

        TextArea specialClosuresReasonField = new TextArea("Reden");

        div.add(closeIcon, specialClosuresDatePicker, specialClosuresReasonField);
        return div;
    }

    private Div createNewOpeningClosures(){
        Div div = new Div();
        div.addClassNames("card", "card-body", "mt-2");

        Icon closeIcon = new Icon(VaadinIcon.CLOSE_SMALL);
        closeIcon.addClassNames("close");

        Select<String> openingHourSelect = new Select<>();
        openingHourSelect.setItems("Maandag", "Dinsdag","Woensdag","Donderdag","Vrijdag","Zaterdag","Zondag");
        openingHourSelect.setLabel("Openingdagen");

        TimePicker openingHourStartTimePicker = new TimePicker();
        openingHourStartTimePicker.setLabel("Openinguren");
        TimePicker openingHourEndTimePicker = new TimePicker();
        openingHourEndTimePicker.setLabel("Sluitinguren");
        openingHourEndTimePicker.addClassNames("timepicker");
        NumberField maxklantenField = new NumberField();

        div.add(closeIcon, openingHourSelect, openingHourStartTimePicker, openingHourEndTimePicker);
        return div;
    }
}
