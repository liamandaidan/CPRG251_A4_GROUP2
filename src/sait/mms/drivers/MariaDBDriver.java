/**
 * 
 */
package sait.mms.drivers;

import java.sql.*;
import sait.mms.contracts.DatabaseDriver;

/**
 * @author Ali
 *
 */
public class MariaDBDriver implements DatabaseDriver {
	private static final String SERVER = "localhost";
	private static final int PORT = 3306;
	private static final String DATABASE = "cprg251";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";
	Connection connection;

	/**
	 * Connects the program to the DB.
	 * @throws SQLException Throws an SQL Exception.
	 */
	@Override
	public void connect() throws SQLException {
		System.out.println("connection worked!");
		String dsn = getDsn();
		connection = DriverManager.getConnection(dsn);

	}
/**
 * Gets the DSN.
 * @return Returns the formatted DSN to connect the DB.
 */
	private String getDsn() {
		String dsn = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", SERVER, PORT, DATABASE, USERNAME,
				PASSWORD);
		return dsn;
	}

	/**
	 * DisConnects the connection to the DB.
	 * @throws SQLException Throws an SQL Exception.
	 */
	@Override
	public void disconnect() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			System.out.println("Disconnected!");
			connection.close();
		}
	}

	/**
	 * Used to retrieve data from the DB.
	 * @param query The SQL query to be sent to the DB.
	 * @return Returns the results of the SQL statement.
	 */
	@Override
	public ResultSet get(String query) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet results = stmt.executeQuery(query);
		return results;
	}
/**
 * Used to update data in the database.
 * @param query SQL query to be run in the DB.
 * @return Returns the number of rows updated from the query.
 */
	@Override
	public int update(String query) throws SQLException {
		Statement stmt = connection.createStatement();
		int rows = stmt.executeUpdate(query);
		return rows;
	}

}
