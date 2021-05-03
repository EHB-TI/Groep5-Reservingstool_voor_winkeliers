package ehb.group5.app.backend.data.table;

import io.requery.*;
import io.requery.query.MutableResult;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "tickets")
@ToString
public class Ticket {

    @Key @Generated
    int id;

    @Column(name = "date_created")
    Timestamp dateCreated;

    int status;

    @OneToMany
    MutableResult<TicketMessageEntity> ticketMessages;
}
