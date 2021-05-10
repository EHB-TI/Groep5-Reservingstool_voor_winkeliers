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
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import ehb.group5.app.UI.layouts.CommonLayout;
import ehb.group5.app.backend.data.table.CompanyEntity;
import com.vaadin.flow.component.html.H1;

@Route("edit")
@PageTitle("Profiel")
@CssImport("./styles/style.css")
public class EditView extends CommonLayout {

     /*
     Author: Zakaria Lamsakam
     email: zakaria.lamsakam@student.ehb.be
     */

    public EditView(){

        CompanyEntity company = (CompanyEntity) VaadinSession.getCurrent().getAttribute("company");

        Div div = new Div();

        //Titel aanmaken
        div.add(new H1("Edit"));


        Image image = new Image("https://dummyimage.com/400x400/000/fff", "DummyImage");
        div.add(image);

        //Afbeelding toevoegen
        Button button = new Button("browse picture");
        div.add(button);

        //omschrijving toevoegen
        TextArea textArea = new TextArea("Omschrijving");
        textArea.setPlaceholder("Schrijf hier");

        div.add(textArea);

        //Adres toevoegen
        TextField adres = new TextField("Adres");
        div.add(adres);

        //Vaadin icon toevoegen
        Icon edit = new Icon(VaadinIcon.HOME);
        div.add(edit);

        //Telefoonnummer toevoegen
        TextField telefoon = new TextField("Telefoonnummer");
        div.add(telefoon);

        //Vaadin icon toevoegen
        Icon edit1 = new Icon(VaadinIcon.PHONE);
        div.add(edit1);

        ComboBox<String> openingdagen = new ComboBox<>();
        openingdagen.setItems("Maandag", "Dinsdag","Woensdag","Donderdag","Vrijdag","Zaterdag","Zondag");
        openingdagen.setLabel("openingdagen ");


        //maximum tijd implementeren in de winkel

        ComboBox<String> maximumtijd = new ComboBox<>();
        openingdagen.setItems("5 minuten", "10 minuten","15 minuten","20 minuten","25 minuten","30 minuten","35 minuten");
        openingdagen.setLabel("maximumtijd in de winkel ");



        //openinguren en sluitinguren toevoegen
        TimePicker openinguren = new TimePicker();
        openinguren.setLabel("openinguren");
        TimePicker sluitinguren = new TimePicker();
        sluitinguren.setLabel("sluitinguren");


        div.add(openingdagen,openinguren,sluitinguren);

        //Speciale sluiting toevoegen
        DatePicker datePicker = new DatePicker();
        datePicker.setLabel("Speciale sluiting");



        div.add(datePicker);

        //oorzaak toevoegen
        TextArea oorzaak = new TextArea("De oorzaak");
        textArea.setPlaceholder("Schrijf hier");

        div.add(oorzaak);

        Button button1 = new Button("Save changes");
        div.add(button1);


        div.addClassName("centered-content");
        getContainer().add(div);

    }

}
