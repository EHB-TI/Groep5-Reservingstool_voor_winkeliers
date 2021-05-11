package ehb.group5.app.backend.data.table;

import io.requery.*;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "bills")
@ToString
public abstract class Bill {

    @Key @Generated
    int id;

    @Column(name = "company_id")
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    CompanyEntity company;

    @Column
    double amount;

    @Column(value = "CURRENT_TIMESTAMP")
    Timestamp date;

    @Column(length = 10)
    String method;
}
