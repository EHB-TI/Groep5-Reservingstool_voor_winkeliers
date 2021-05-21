package ehb.group5.app.UI.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ehb.group5.app.UI.layouts.CommonLayout;
import org.apache.catalina.webresources.FileResource;

import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

@Route("EditInfo")
@PageTitle("Edit Page")
@CssImport("./styles/EditInfo.css")


public class EditInfoView extends CommonLayout {

    public EditInfoView(){
        H1 Edit = new H1("Edit");
        Edit.setClassName("editinfo-heading");
        add(Edit);
        Paragraph p = new Paragraph("Lorem Ipsum");
        p.setClassName("editinfo-p");
        add(p);

        Image image = new Image("/frontend/default-picture-edit.jpg", "default picture");
        image.setClassName("editinfo-image");
        add(image);

        MemoryBuffer memoryBuffer = new MemoryBuffer();

        Upload upload = new Upload(memoryBuffer);
        upload.addFinishedListener(e -> {
            InputStream inputStream = memoryBuffer.getInputStream();
            // read the contents of the buffered memory
            // from inputStream
        });

        add(upload);
    }
}
