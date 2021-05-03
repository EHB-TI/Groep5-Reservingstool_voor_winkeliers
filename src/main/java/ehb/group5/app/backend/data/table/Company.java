package ehb.group5.app.backend.data.table;

import io.requery.*;
import io.requery.query.MutableResult;
import lombok.ToString;

@Entity
@Table(name = "companies")
@ToString
public abstract class Company {

    @Key @Generated
    int id;

    @Column(length = 30)
    String email;

    @Column(length = 50)
    String password;

    int credits;

    @OneToMany
    MutableResult<BillEntity> bills;
}
