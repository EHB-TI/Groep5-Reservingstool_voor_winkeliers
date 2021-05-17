package ehb.group5.app.backend.data.table;

import ehb.group5.app.backend.data.DatabaseService;
import io.requery.*;
import io.requery.query.MutableResult;
import lombok.ToString;

@Entity
@Table(name = "customers")
@ToString
public abstract class Customer {

    @Key
    @Generated
    int id;

    @Column(name = "first_name", length = 20)
    String firstName;

    @Column(name = "last_name", length = 20)
    String lastName;

    @Column
    String email;

    @Column
    String password;

    @OneToMany(mappedBy = "customer_id")
    MutableResult<ReservationEntity> reservations;

    @OneToMany(mappedBy = "customer_id")
    MutableResult<ReviewEntity> reviews;

    @OneToMany(mappedBy = "customer_id")
    MutableResult<TicketEntity> tickets;

    @OneToMany(mappedBy = "customer_id")
    MutableResult<TicketMessageEntity> ticketMessages;
}
