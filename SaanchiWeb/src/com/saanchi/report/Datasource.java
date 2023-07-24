package com.saanchi.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.naming.*;

import org.apache.log4j.Logger;

public class Datasource {

	private final static Logger logger = Logger.getLogger(Datasource.class);

	public static Connection getDataSource() throws NamingException {
		Connection conn = null;
		try {

			/*
			 * String driverURL = "org.postgresql.Driver"; String userId ="postUser"; String
			 * password = "postUser"; String dbURL
			 * ="jdbc:postgresql://mydbinstance.cnmowwfza8pd.ap-south-1.rds.amazonaws.com:5432/PFT";
			 */

			String driverURL = "org.postgresql.Driver";
			String userId = "uatdbadmin";
			String password = "calcutta#106";
			String dbURL = "jdbc:postgresql://13.42.106.45:5432/uatsanchidb";

			try {
				Class.forName(driverURL).newInstance();
				conn = DriverManager.getConnection(dbURL, userId, password);

			} catch (Exception e1) {
				System.out.println("e1----------------------------------" + e1);
				e1.printStackTrace();
			}

			return conn;

		} catch (Exception ex) {
			logger.fatal("Exception---", ex);
			throw new RuntimeException("Database Connection Not Found Exception.");
		}
	}

	public static void closeConnection(Connection conn) {
		try {

			conn.close();
			if (logger.isInfoEnabled())
				logger.debug("Connection Closed");
		} catch (Exception e) {
			logger.fatal("Exception---", e);
			throw new RuntimeException(e);
		}
	}

}
