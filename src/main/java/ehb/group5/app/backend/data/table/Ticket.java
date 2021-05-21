package ehb.group5.app.backend.data.table;

import ehb.group5.app.backend.data.DatabaseService;
import io.requery.*;
import io.requery.query.MutableResult;
import io.requery.query.Result;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "tickets")
@ToString
public class Ticket {

    @Key
    @Generated
    int id;

    String title;

    @Column(name = "date_created")
    Timestamp dateCreated;

    @Column(name = "company_id", nullable = true)
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    CompanyEntity company;

    @Column(name = "customer_id", nullable = true)
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    CustomerEntity customer;

    int status;

    @OneToMany(mappedBy = "ticket_id")
    MutableResult<TicketMessageEntity> ticketMessages;

    public static Result<TicketEntity> getAllTickets(){
        return DatabaseService.getTicketStore()
                .select(TicketEntity.class)
                .get();
    }

    public static Result<TicketEntity> getAllClosedTickets(){
        return DatabaseService.getTicketStore()
                .select(TicketEntity.class)
                .where(TicketEntity.STATUS.eq(Status.CLOSED))
                .get();
    }

    public static Result<TicketEntity> getAllOpenedTickets(){
        return DatabaseService.getTicketStore()
                .select(TicketEntity.class)
                .where(TicketEntity.STATUS.eq(Status.OPENED))
                .get();
    }


    public final class Status {
        public static final int CLOSED = 0;
        public static final int OPENED = 1;
    }
}
