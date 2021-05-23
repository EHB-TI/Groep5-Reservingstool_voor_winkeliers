package ehb.group5.app.backend.data.table;

import io.requery.*;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "ticket_messages")
@ToString
public class TicketMessage {

    @Key
    @Generated
    int id;

    @Column(name = "ticket_id")
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    TicketEntity ticket;

    @Column(name = "company_id", nullable = true)
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    CompanyEntity company;

    @Column(name = "customer_id", nullable = true)
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    CustomerEntity customer;

    @Column(name = "admin_id", nullable = true)
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    AdminEntity admin;

    String message;

    Timestamp date;
}