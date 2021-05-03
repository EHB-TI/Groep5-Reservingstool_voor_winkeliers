package ehb.group5.app.backend.data;

import com.zaxxer.hikari.HikariDataSource;
import ehb.group5.app.backend.config.SQLCredentials;
import ehb.group5.app.backend.data.table.*;
import io.requery.sql.EntityDataStore;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class DatabaseService {

    @Getter
    private final static Logger logger = Logger.getLogger(DatabaseService.class.getName());
    @Getter
    private static EntityDataStore<BillEntity> billsStore;
    @Getter
    private static EntityDataStore<CompanyEntity> companiesStore;
    @Getter
    private static EntityDataStore<CustomerEntity> customersStore;
    @Getter
    private static EntityDataStore<OpeningHourEntity> openingHoursStore;
    @Getter
    private static EntityDataStore<ReservationEntity> reservationsStore;
    @Getter
    private static EntityDataStore<ReviewEntity> reviewsStore;
    @Getter
    private static EntityDataStore<SpecialClosureEntity> specialClosuresStore;
    @Getter
    private static EntityDataStore<StoreEntity> storesStore;
    @Getter
    private static EntityDataStore<TicketEntity> ticketsStore;
    @Getter
    private static EntityDataStore<TicketMessageEntity> ticketMessagesStore;
    @Getter
    private HikariDataSource hikari;

    public DatabaseService() {
        getLogger().info("Connection to the database...");
        hikari = new HikariDataSource(new SQLCredentials().toHikari());

        getLogger().info("Setting up Entities Data Store...");
        billsStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        companiesStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        customersStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        openingHoursStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        reservationsStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        reviewsStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        specialClosuresStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        storesStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        ticketsStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        ticketMessagesStore = new EntityDataStore<>(hikari, Models.DEFAULT);
    }
}
