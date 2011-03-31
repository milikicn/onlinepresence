/**
 * 
 */
package net.onlinepresence.opos.semanticstuff;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import net.onlinepresence.opos.config.Config;
import net.onlinepresence.opos.config.Settings;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

import com.hp.hpl.jena.sdb.sql.JDBC;

public class ConnectionPool {

	private static class ConnectionPoolHolder {
		private static final ConnectionPool INSTANCE = new ConnectionPool();
	}

	private Logger logger = Logger.getLogger(this.getClass().getName());

	private DataSource ds;
	private GenericObjectPool pool;

	private ConnectionPool() {
		logger.info("Initializing JDBC connection pool");
		
		// get DB settings from config file
		String dbType = Settings.getInstance().config.sdbConfig.dbType;
		String jdbcURL = Settings.getInstance().config.sdbConfig.dbURL;
		String user = Settings.getInstance().config.sdbConfig.dbUser;
		String pw = Settings.getInstance().config.sdbConfig.dbPassword;


		// MySQL
		if (Config.MySQL.equalsIgnoreCase(dbType)) {
			JDBC.loadDriverMySQL();
		} else
			// TODO
			;

		pool = new GenericObjectPool();
		pool.setMinIdle(5);
		pool.setMaxActive(15);
		pool.setTestOnBorrow(true);
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
				jdbcURL, user, pw);

		@SuppressWarnings("unused")
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
				connectionFactory, pool, null, "SELECT 1;", false, true);

		ds = new PoolingDataSource(pool);
	}

	/**
	 * 
	 * @return
	 */
	public static ConnectionPool getInstance() {
		return ConnectionPoolHolder.INSTANCE;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

}
