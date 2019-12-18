package vezbanje;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import rs.ac.uns.ftn.db.jdbc.connection.ConnectionParams; 



public class ConnectionUtil_HikariCP {
	private static HikariConfig hikariConfig = new HikariConfig();
	private static HikariDataSource hikariDS;
	
	static { 
		hikariConfig.setJdbcUrl(ConnectionParams.LOCAL_CONNECTION_STRING);
		hikariConfig.setUsername(ConnectionParams.USERNAME);//Exxxxx
		hikariConfig.setPassword(ConnectionParams.PASSWORD);
		hikariConfig.setMaximumPoolSize(5);
		hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
		hikariDS = new HikariDataSource(hikariConfig);
	}
	
	public static Connection getConnection() throws SQLException {
		return hikariDS.getConnection();

	}

}
