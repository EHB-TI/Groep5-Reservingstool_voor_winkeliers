package ehb.group5.app.backend.data.table;

import io.requery.*;
import io.requery.query.MutableResult;
import lombok.ToString;

@Entity
@Table(name = "admins")
@ToString
public class Admin {

    @Key
    @Generated
    int id;

    @Column(length = 50)
    String email;

    @Column(length = 50)
    String password;

    @OneToMany(mappedBy = "admin_id")
    MutableResult<TicketMessageEntity> ticketMessages;
}
