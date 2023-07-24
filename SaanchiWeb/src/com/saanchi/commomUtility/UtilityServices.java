package com.saanchi.commomUtility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;

public class UtilityServices {

	private static final Logger log = Logger.getLogger(UtilityServices.class);
	
	private static final String USERSql = "SELECT event_code,user_group FROM group_event_map ORDER BY event_code";
		
	
	

	public List<CommonOptionsVO> getUserLOV() {
	
		List<CommonOptionsVO> lovMap = new ArrayList<CommonOptionsVO>();
		Connection con = null;
		ResultSet rs = null;
		CommonOptionsVO optionsVO = null;
		try {
			con = Datasource.getDataSource();
			rs = con.prepareStatement(USERSql).executeQuery();
			while (rs.next()) {
				optionsVO = new CommonOptionsVO(rs.getString("user_group"),
						rs.getString("event_code"));
				lovMap.add(optionsVO);
				}

			
		} catch (Exception e) {
			log.fatal("Cannot load key ", e);
		}

		finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					log.fatal("Cannot close Resultset after getting User " , e);
				}

			if (null != con)
				try {
					con.close();
				} catch (SQLException e) {
					log.fatal(
							"Cannot close connection after getting User  LOV",
							e);
				}
		}
		return lovMap;
	}

	
	
	
	
	
}
