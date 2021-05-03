package ehb.group5.app.backend.config;

import com.zaxxer.hikari.HikariConfig;
import lombok.val;

public class SQLCredentials {
    private String host = "dt5.ehb.be";
    private String database = "2021PROGPROJGR5";
    private String username = "2021PROGPROJGR5";
    private String password = "8uGuEtMV";
    private int poolSize = 5;
    private int port = 3306;

    public HikariConfig toHikari(){
        val config = new HikariConfig();

        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(poolSize);

        config.addDataSourceProperty("autoReconnect", true);

        config.setConnectionTimeout(30000);

        return config;
    }
}
