package ehb.group5.app.backend.data.table;

import io.requery.*;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * Geinspireerd met deze voorbeelden
 * https://github.com/requery/requery/wiki/Query-examples
 * @author Arnaud Faille
 */
@Entity
@Table(name = "reservations")
@ToString
public abstract class Reservation {

    @Key @Generated
    int id;

    @Column(name = "store_id")
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    StoreEntity store;

    @Column(name = "customer_id")
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    CustomerEntity customer;

    Timestamp date;
}
