package ehb.group5.app.backend.data.table;

import io.requery.*;
import io.requery.query.MutableResult;
import lombok.ToString;

@Entity
@Table(name = "customers")
@ToString
public abstract class Customer {

    @Key
    @Generated
    protected int id;

    @Column(name = "first_name", length = 20)
    protected String firstName;

    @Column(name = "last_name", length = 20)
    protected String lastName;

    @Column
    protected String email;

    @Column
    protected String password;

    @Column
    protected int type;

    @OneToMany(mappedBy = "customer_id")
    protected MutableResult<ReservationEntity> reservations;

    @OneToMany(mappedBy = "customer_id")
    protected MutableResult<ReviewEntity> reviews;
}
