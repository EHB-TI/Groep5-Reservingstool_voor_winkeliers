package ehb.group5.app.backend.data.table;

import io.requery.*;
import lombok.ToString;

import java.sql.Time;

@Entity
@Table(name = "opening_hours")
@ToString
public abstract class OpeningHour {

    @Key @Generated
    protected int id;

    @Column(name = "store_id")
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    protected StoreEntity store;

    @Column(name = "week_day")
    protected int weekDay;

    @Column(name = "begin_hour")
    protected Time beginHour;

    @Column(name = "end_hour")
    protected Time endHour;
}
