/**
 * 
 */
package sait.mms.managers;

import java.sql.*;
import java.time.Duration;
import java.util.*;
import sait.mms.drivers.MariaDBDriver;
import sait.mms.problemdomain.Movie;

/**
 * @author Benson
 *
 */
public class MovieManagementSystem {

	private MariaDBDriver md;
	private Scanner in;

	/**
	 * No arg constructor for Movie Management System.
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

	/**
	 * @author Robyn
	 * @throws SQLException
	 */

	public void displayMenu() throws SQLException {

		String title;
		int id, duration, year;

		int option;
		boolean valid = false;

		in = new Scanner(System.in);

		do {

			System.out.printf("Jim's Movie Manager%n" + "1. Add New Movie%n" + "2. Print movies released in year%n"
					+ "3. Print random list of movies%n" + "4. Delete a movie%n" + "5. Exit%n%n");

			System.out.printf("Enter option: ");

			try {
				option = in.nextInt();
				in.nextLine(); // flush the line
				switch (option) {
				case 1:
					System.out.println("Enter movie title: ");
					title = in.nextLine();
					System.out.println("Enter duration: ");
					duration = in.nextInt();
					System.out.println("Enter year: ");
					year = in.nextInt();
					addMovie(duration, title, year);
					break;
				case 2:
					System.out.println("Enter in year: ");
					year = in.nextInt();
					printMoviesInYear(year);
					break;
				case 3:
					//System.out.println("Enter number of movies: ");
					printRandomMovies();
					break;
				case 4:
					System.out.println("Enter the movie ID you want to delete: ");
					id = in.nextInt();
					deleteMovie(id);
					break;
				case 5:
					valid = true;
					break;
				default:
					System.out.println("Please enter a number from 1 - 5.\n");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Please enter a number from 1 - 5.\n");
				in.next(); // clear the input
				continue;
			}

		} while (!valid);

		// exit
		md.disconnect();
		in.close();

	}

	/**
	 * 
	 * @param id    the movie id
	 * @param dur   the movie duration
	 * @param title the movie title
	 * @param yr    the year the movie released
	 */
	public void addMovie(int dur, String title, int yr) throws SQLException {
		Movie film = new Movie(dur, title, yr);
		String sqlStatement = "INSERT INTO movies(duration, title, year) VALUES(" + film.getDuration() + ",'"
				+ film.getTitle() + "'," + film.getYear() + ");";
		int rows = md.update(sqlStatement);
		System.out.println(rows + " rows added to database.");

	}

	/**
	 * @param yr the year to display
	 */
	public void printMoviesInYear(int yr) throws SQLException {
		String sqlStatement = "SELECT * FROM movies WHERE year = " + yr + ";";
		ResultSet result = md.get(sqlStatement);
		int numResults = result.getFetchSize();
		int counter = 1;
		while (result.next()) {
			System.out.println(result.getString(counter));
			counter++;
		}
	}

	/**
	 * @throws SQLException
	 * 
	 */
	public void printRandomMovies() throws SQLException {
		// Step 1 select how many movies there are total
		String sqlStmt = "SELECT COUNT(id) FROM movies";
		ResultSet randNum = md.get(sqlStmt);
		randNum.next();
		int rand = (int) (randNum.getInt(1) * Math.random());
		String sqlInp = "SELECT * FROM movies";
		// Step 2 select a random movie
		ResultSet result = md.get(sqlInp);
		int id, duration, year;;
		String title;
		Movie m;
		for (int i = 0; i <= rand && result.next(); i++) {// loop through movies until we reach a random index. Since the
															// ID could be deleted.
			if (i == rand) {
				id = result.getInt("id");
				System.out.println(id);
				duration = result.getInt(1);
				System.out.println(duration);
				title = result.getString(2);
				System.out.println(title);
				year = result.getInt(3);
				m = new Movie(id, duration, title, year);
				System.out.println(m);
			}

			
		}
		// Watch out for an index removal case. EG movie at ID 2 missing
	}

	/**
	 * 
	 */
	public void deleteMovie(int movieId) {

		// take movie id
		try {
			String sqlStmt = String.format("DELETE FROM movies WHERE id = %s", movieId);

			int rows = md.update(sqlStmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
