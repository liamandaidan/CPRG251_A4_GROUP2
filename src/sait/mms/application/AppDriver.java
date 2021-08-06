/**
 * 
 */
package sait.mms.application;

import java.sql.SQLException;

import sait.mms.managers.MovieManagementSystem;

/**
 * This class will call the movie managment system.
 * 
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
