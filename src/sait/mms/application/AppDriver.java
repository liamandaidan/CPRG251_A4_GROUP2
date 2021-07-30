/**
 * 
 */
package sait.mms.application;

import java.sql.ResultSet;
import java.sql.SQLException;

import sait.mms.drivers.MariaDBDriver;


/**
 * @author liamm
 *
 */
public class AppDriver {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		MariaDBDriver md = new MariaDBDriver();
		md.connect();
		String sqlQuery = "Select * from movies";
		ResultSet results = md.get(sqlQuery);
		while(results.next()) {
			int id = results.getInt(1);
			String name = results.getString(2);
			System.out.printf("%d, %s\n",id,name);	
		}
		md.disconnect();
		

 
	}

}
