package ehb.group5.app.backend.data.table;

import io.requery.*;
import lombok.ToString;

/**
 * Geinspireerd met deze voorbeelden
 * https://github.com/requery/requery/wiki/Query-examples
 * @author Arnaud Faille
 */
@Entity
@Table(name = "reviews")
@ToString
public abstract class Review {

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

    int score;

    String description;
}
