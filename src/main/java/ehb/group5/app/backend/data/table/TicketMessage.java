package ehb.group5.app.backend.data.table;

import io.requery.*;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "ticket_messages")
@ToString
public class TicketMessage {

    @Key @Generated
    int id;

    @Column(name = "ticket_id")
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    Ticket ticket;

    String message;

    Timestamp date;

    @Column(name = "file_id")
    int fileId;
}
