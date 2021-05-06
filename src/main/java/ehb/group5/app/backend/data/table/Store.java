package ehb.group5.app.backend.data.table;

import io.requery.*;
import io.requery.query.MutableResult;
import lombok.ToString;

@Entity
@Table(name = "stores")
@ToString
public abstract class Store {

    @Key
    @Generated
    protected int id;

    @Column(name = "company_id")
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    protected CompanyEntity company;

    protected String name;

    protected String adress;

    @Column(name = "post_code")
    protected String postCode;

    protected String description;

    @Column(name = "phone_number")
    protected String phoneNumber;

    protected String postbus;

    @Column(name = "max_customers")
    protected int maxCustomers;

    @Column(name = "logo_id")
    protected int logoId;

    @Column(name = "banner_id")
    protected int bannerId;

    @OneToMany(mappedBy = "store_id")
    protected MutableResult<ReservationEntity> reservations;

    @OneToMany(mappedBy = "store_id")
    protected MutableResult<OpeningHourEntity> openingHours;

    @OneToMany(mappedBy = "store_id")
    protected MutableResult<SpecialClosureEntity> specialClosures;
}
