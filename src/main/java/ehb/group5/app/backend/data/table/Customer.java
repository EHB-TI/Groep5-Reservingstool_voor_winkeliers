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
    int id;

    @Column(name = "first_name", length = 20)
    String firstName;

    @Column(name = "last_name", length = 20)
    String lastName;

    @Column
    String email;

    @Column
    String password;

    @Column
    int type;

    @OneToMany(mappedBy = "customer_id")
    MutableResult<ReservationEntity> reservations;

    @OneToMany(mappedBy = "customer_id")
    MutableResult<ReviewEntity> reviews;
}
