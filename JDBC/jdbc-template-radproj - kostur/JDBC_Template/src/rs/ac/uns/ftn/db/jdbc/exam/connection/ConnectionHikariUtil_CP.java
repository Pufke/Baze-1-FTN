package rs.ac.uns.ftn.db.jdbc.exam.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionHikariUtil_CP {

	private static HikariConfig hikariConfig =  new HikariConfig();
	private static HikariDataSource HikariDS = new HikariDataSource();
	
	static{
		hikariConfig.setUsername(ConnectionParams.USERNAME);
		hikariConfig.setPassword(ConnectionParams.PASSWORD);
		hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
		hikariConfig.setMaximumPoolSize(5);
		hikariConfig.addDataSourceProperty("", "");
		
		HikariDS = new HikariDataSource(hikariConfig);
	}
	
	
	
	
	private ConnectionHikariUtil_CP() {}

	public Connection getConnection() throws SQLException {
		return HikariDS.getConnection();
	}
}
