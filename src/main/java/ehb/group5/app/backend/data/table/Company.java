package ehb.group5.app.backend.data.table;

import ehb.group5.app.backend.data.DatabaseService;
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
