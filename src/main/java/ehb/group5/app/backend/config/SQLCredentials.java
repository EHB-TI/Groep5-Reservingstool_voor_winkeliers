package ehb.group5.app.backend.config;

import com.zaxxer.hikari.HikariConfig;
import lombok.Data;
import lombok.val;

@Data
public class SQLCredentials {
    private String host, database, username, password = "";
    private int poolSize = 5;
    private int port = 3306;

    public HikariConfig toHikari(){
        val config = new HikariConfig();

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(poolSize);

        config.addDataSourceProperty("autoReconnect", true);

        config.setConnectionTimeout(30000);

        return config;
    }
}
