/**
 * 
 */
package sait.mms.application;

import java.sql.ResultSet;
import java.sql.SQLException;

import sait.mms.drivers.MariaDBDriver;
import sait.mms.managers.MovieManagementSystem;


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
		
		new MovieManagementSystem();
	}

}
