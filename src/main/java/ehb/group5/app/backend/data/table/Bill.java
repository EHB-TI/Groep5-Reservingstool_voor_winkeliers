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
    @ForeignKey(update = ReferentialAction.RESTRICT, referencedColumn = "id")
    @ManyToOne
    protected CompanyEntity companyId;

    @Column
    protected double amount;

    @Column(value = "CURRENT_TIMESTAMP")
    protected Timestamp date;

    @Column(length = 10)
    String method;
}
