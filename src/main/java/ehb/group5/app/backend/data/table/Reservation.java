package ehb.group5.app.backend.data.table;

import io.requery.*;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "reservations")
@ToString
public abstract class Reservation {

    @Key @Generated
    int id;

    @Column(name = "store_id")
    @ForeignKey(update = ReferentialAction.RESTRICT, referencedColumn = "id")
    @ManyToOne
    StoreEntity store;

    @Column(name = "customer_id")
    @ForeignKey(update = ReferentialAction.RESTRICT, referencedColumn = "id")
    @ManyToOne
    CustomerEntity customer;

    Timestamp date;
}
