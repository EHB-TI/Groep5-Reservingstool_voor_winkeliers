package ehb.group5.app.backend.data.table;

import io.requery.*;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "special_closures")
@ToString
public class SpecialClosure {

    @Key @Generated
     int id;

    @Column(name = "store_id")
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
     StoreEntity store;

     Date date;

     String reason;
}
