package ehb.group5.app.backend.data.table;

import io.requery.*;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "reservations")
@ToString
public abstract class Reservation {

    @Key @Generated
    protected int id;

    @Column(name = "store_id")
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    protected StoreEntity store;

    @Column(name = "customer_id")
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    protected CustomerEntity customer;

    protected Timestamp date;
}
