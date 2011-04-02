package net.onlinepresence.opos.semanticstuff.rdfpersistance;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sdb.SDBFactory;
import com.hp.hpl.jena.sdb.Store;
import com.hp.hpl.jena.sdb.StoreDesc;
import com.hp.hpl.jena.sdb.sql.SDBConnection;
import com.hp.hpl.jena.sdb.store.DatabaseType;
import com.hp.hpl.jena.sdb.store.LayoutType;

public class RepositoryDataProvider implements DataProvider {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	private StoreDesc storeDesc;
	private SDBConnection conn;
	private Store store;

	public RepositoryDataProvider() {

		// MySQL
		storeDesc = new StoreDesc(LayoutType.LayoutTripleNodesHash,
				DatabaseType.MySQL);

		// get the connection
		establishConnection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.intelleo.services.datacentral.DataProvider#getDataModel()
	 */
	public Model getDataModel(boolean reconnect) {
		Model dataModel = null;

		try {
			// // create the necessary tables - cleans the db
			// if (Settings.getInstance().config.populateDB && !reconnect) {
			logger.debug("Formatting db tables...");
			store.getTableFormatter().create();
			// }
			// get the data model
			establishConnection();
			logger.debug("Connecting default data model...");
			dataModel = SDBFactory.connectDefaultModel(store);

		} catch (Exception e) {
			logger.error("Could not load DataModel from database!", e);
		}

		return dataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.intelleo.services.datacentral.DataProvider#flushDataModel(com.hp.
	 * hpl.jena.rdf.model.Model)
	 */
	public void flushDataModel(Model dataModel) {
		// nothing to do here. data is automatically flushed to database.
		// well, should be...
		store.close();
		conn.close();
	}

	/**
	 * 
	 */
	private void establishConnection() {
		try {
			conn = new SDBConnection(ConnectionPool.getInstance().getConnection());
			logger.debug("Got SQL connection (is closed? : " + conn.getSqlConnection().isClosed() + ")");
			store = SDBFactory.connectStore(conn, storeDesc);
		} catch (SQLException e) {
			logger.error("Could not get JDBC Connection for SDB store", e);
			// TODO: throw exception? retry?
		}
	}
}
