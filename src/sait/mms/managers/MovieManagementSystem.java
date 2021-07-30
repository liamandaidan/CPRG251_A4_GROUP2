/**
 * 
 */
package sait.mms.managers;

import java.sql.*;
import java.util.*;
import sait.mms.drivers.MariaDBDriver;
import sait.mms.problemdomain.Movie;

/**
 * @author Benson
 *
 */
public class MovieManagementSystem {

	private int id;
	private int duration;
	private String title;
	private int year;
	private MariaDBDriver md;
	private Scanner in;  
	

	/**
	*
	*
	*/
	public MovieManagementSystem() {
		try {
			md = new MariaDBDriver();
			md.connect();
			displayMenu();
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
		}

	}

	public void displayMenu() throws SQLException {

		
			// exit
				md.disconnect();	
		 in = new Scanner(System.in);
		int option;

		System.out.printf("Jim's Movie Manager%n" + "1. Add New Movie%n" + "2. Print movies released in year%n"
				+ "3. Print random list of movies%n" + "4. Delete a movie%n" + "5. Exit");
		//SELECT COUNT(*) FROM movies
		System.out.printf("Enter an option: ");
		option = in.nextInt();

	}

	

	/**
	 * 
	 * @param theMovie
	 */
	public void addMovie(int id, int dur, String title, int yr) {
		Movie film = new Movie(id, dur, title, yr);
		String sqlStatement = "INSERT INTO movies(id, duration, title, year) VALUES(" + film.getId() + "," + film.getDuration() + ",'"
				+ film.getTitle() + "'," + film.getYear() + ");";
		//int rows = md.update(sqlStatement);

	}

	/**
	 * 
	 */
	public void printMoviesInYear() {

	}

	/**
	 * 
	 */
	public void printRandomMovies() {

	}

	/**
	 * 
	 */
	public void deleteMovie(Movie film) {

	}
}
