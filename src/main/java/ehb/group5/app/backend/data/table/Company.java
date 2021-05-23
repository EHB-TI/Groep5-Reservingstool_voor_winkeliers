package ehb.group5.app.backend.data.table;

import ehb.group5.app.backend.data.DatabaseService;
import io.requery.*;
import io.requery.query.MutableResult;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * Geinspireerd met deze voorbeelden
 * https://github.com/requery/requery/wiki/Query-examples
 * @author Arnaud Faille
 */
@Entity
@Table(name = "companies")
@ToString
public abstract class Company {


    @Key @Generated
    @Column(name = "id")
    int id;

    @Column(length = 50)
    String email;

    @Column(length = 50)
    String password;

    int credits;

    @Column(name = "subscription_expires_date")
    Timestamp subscriptionExpiresDate;


    @OneToMany(mappedBy = "company_id")
    MutableResult<StoreEntity> stores;

    @OneToMany(mappedBy = "company_id")
    MutableResult<TicketEntity> tickets;

    @OneToMany(mappedBy = "company_id")
    MutableResult<TicketMessageEntity> ticketMessages;

    public static CompanyEntity getCompanyById(int id){
        return DatabaseService.getCompaniesStore()
                .select(CompanyEntity.class)
                .where(CompanyEntity.ID.eq(id))
                .get()
                .firstOrNull();
    }
    public static CompanyEntity getCompanyByEmail(String email){
        return DatabaseService.getCompaniesStore()
                .select(CompanyEntity.class)
                .where(CompanyEntity.EMAIL.eq(email))
                .get()
                .firstOrNull();
    }
}
