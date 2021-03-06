package sait.mms.contracts;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * This is the interface for the database
 *
 */
public interface DatabaseDriver {
	/**
	 * Connects to the database.
	 * @throws SQLException
	 */
	void connect() throws SQLException;

	/**
	 * Performs a retrieval from the database (ie: SELECT)
	 * @param query Query to send to database.
	 * @return Returns the results as a ResultSet.
	 * @throws SQLException Thrown if problem performing query.
	 */
	ResultSet get(String query) throws SQLException;

	/**
	 * Performs an update query (UPDATE, DELETE, DROP, etc.) on the database.
	 * @param query Query to send to database.
	 * @return Number of rows modified.
	 * @throws SQLException Throws an SQL Exception.
	 */
	int update(String query) throws SQLException;

	/**
	 * Disconnects from the database.
	 * @throws SQLException Throws an SQL Exception.
	 */
	void disconnect() throws SQLException;
}
