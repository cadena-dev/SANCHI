package com.saanchi.commomUtility;

import java.sql.Connection;
import javax.naming.*;
import org.apache.log4j.Logger;
import java.sql.DriverManager;  

public class Datasource {

	private final static Logger logger = Logger.getLogger(Datasource.class);

	public static Connection getDataSource() throws NamingException {
		Connection conn = null;
		try {

			

			 String DRIVER="org.postgresql.Driver";
			 String DBURL="jdbc:postgresql://13.42.106.45:5432/uatsanchidb";
			 String DBUSER="uatdbadmin";
			 String DBPASSWORD="calcutta#106";

			Class.forName(DRIVER).newInstance();
			conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);


			

		} catch (Exception ex) {
			logger.fatal("Exception---", ex);
			throw new RuntimeException("Database Connection Not Found Exception.");
		}
		return conn;
	}

	public static void closeConnection(Connection conn) {
		try {

			conn.close();
			if (logger.isInfoEnabled())
				logger.debug("Connection Closed");
		} catch (Exception e) {
			logger.fatal("ERROR ", e);
			throw new RuntimeException(e);
		}
	}

}
