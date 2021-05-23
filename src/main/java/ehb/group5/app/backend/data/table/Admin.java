package ehb.group5.app.backend.data.table;

import ehb.group5.app.backend.data.DatabaseService;
import io.requery.*;
import io.requery.query.MutableResult;
import lombok.ToString;

/**
 * Geinspireerd met deze voorbeelden
 * https://github.com/requery/requery/wiki/Query-examples
 * @author Arnaud Faille
 */
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

    public static AdminEntity getAdminByEmail(String email){
        return DatabaseService.getAdminStore()
                .select(AdminEntity.class)
                .where(AdminEntity.EMAIL.eq(email))
                .get()
                .firstOrNull();
    }
}
