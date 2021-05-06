package ehb.group5.app.backend.data.table;

import io.requery.*;
import lombok.ToString;

@Entity
@Table(name = "reviews")
@ToString
public abstract class Review {

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

    protected int score;

    protected String description;
}
