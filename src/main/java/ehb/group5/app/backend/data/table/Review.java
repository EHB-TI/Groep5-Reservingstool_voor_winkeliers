package ehb.group5.app.backend.data.table;

import io.requery.*;
import lombok.ToString;

@Entity
@Table(name = "reviews")
@ToString
public abstract class Review {

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

    int score;

    String description;
}
