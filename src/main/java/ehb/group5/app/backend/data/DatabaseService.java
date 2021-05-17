package ehb.group5.app.backend.data;

import com.zaxxer.hikari.HikariDataSource;
import ehb.group5.app.backend.config.SQLCredentials;
import ehb.group5.app.backend.data.table.*;
import ehb.group5.app.backend.utils.LogUtils;
import io.requery.sql.EntityDataStore;
import lombok.Getter;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static ehb.group5.app.backend.utils.Configurations.readOrCreateConfiguration;

@Service
public class DatabaseService {

    @Getter
    private final static Logger logger = Logger.getLogger(DatabaseService.class.getName());
    @Getter
    private static EntityDataStore<AdminEntity> adminStore;
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
    private static EntityDataStore<TicketEntity> ticketStore;
    @Getter
    private static EntityDataStore<TicketMessageEntity> ticketMessageStore;
    @Getter
    private HikariDataSource hikari;

    public DatabaseService() {
        getLogger().info(LogUtils.YELLOW + "Reading sql config file...");
        val sqlConfig = readOrCreateConfiguration(SQLCredentials.class);

        getLogger().info(LogUtils.YELLOW + "Connection to the database...");
        hikari = new HikariDataSource(sqlConfig.toHikari());
        getLogger().info(LogUtils.GREEN + "Connection etablised");

        getLogger().info(LogUtils.YELLOW + "Setting up Entities Data Store...");
        adminStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        companiesStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        customersStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        openingHoursStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        reservationsStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        reviewsStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        specialClosuresStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        storesStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        ticketStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        ticketMessageStore = new EntityDataStore<>(hikari, Models.DEFAULT);
        getLogger().info(LogUtils.GREEN + "Entities Data Store DONE");
    }
}
