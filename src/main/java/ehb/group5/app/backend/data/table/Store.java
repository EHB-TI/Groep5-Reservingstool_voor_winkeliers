package ehb.group5.app.backend.data.table;

import io.requery.*;
import io.requery.query.MutableResult;
import lombok.ToString;

/**
 * Geinspireerd met deze voorbeelden
 * https://github.com/requery/requery/wiki/Query-examples
 * @author Arnaud Faille
 */
@Entity
@Table(name = "stores")
@ToString
public abstract class Store {

    @Key
    @Generated
    @Column(name = "id")
    protected int id;

    @Column(name = "company_id")
    @ForeignKey(referencedColumn = "id")
    @ManyToOne
    CompanyEntity company;

    String name;

    String adress;

    @Column(name = "post_code")
    int postCode;

    String description;

    @Column(name = "phone_number")
    String phoneNumber;

    String postbus;

    @Column(name = "max_customers")
    int maxCustomers;

    @Column(name = "logo_id")
    int logoId;

    @Column(name = "banner_id")
    int bannerId;

    @Column(name = "max_time")
    int maxTime;

    @OneToMany(mappedBy = "store_id")
    MutableResult<ReservationEntity> reservations;

    @OneToMany(mappedBy = "store_id")
    MutableResult<OpeningHourEntity> openingHours;

    @OneToMany(mappedBy = "store_id")
    MutableResult<SpecialClosureEntity> specialClosures;
}
