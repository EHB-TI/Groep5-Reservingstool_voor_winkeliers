package ehb.group5.app.backend.data.table;

import ehb.group5.app.backend.data.DatabaseService;
import io.requery.*;
import io.requery.query.MutableResult;
import io.requery.query.Result;
import lombok.ToString;

import java.sql.Timestamp;

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

    @Column(name = "subscription_expires_date")
    Timestamp subscriptionExpiresDate;

    @OneToMany(mappedBy = "company_id")
    MutableResult<StoreEntity> stores;

    public static CompanyEntity getCompanyById(int id){
        return DatabaseService.getCompaniesStore()
                .select(CompanyEntity.class)
                .where(CompanyEntity.ID.eq(id))
                .get()
                .firstOrNull();
    }
}
