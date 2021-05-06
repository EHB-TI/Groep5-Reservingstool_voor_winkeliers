package ehb.group5.app.backend.data.table;

import io.requery.*;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "bills")
@ToString
public abstract class Bill {

    @Key @Generated
    protected int id;

    @Column(name = "company_id")
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    protected CompanyEntity company;

    @Column
    protected double amount;

    @Column(value = "CURRENT_TIMESTAMP")
    protected Timestamp date;

    @Column(length = 10)
    protected String method;
}
