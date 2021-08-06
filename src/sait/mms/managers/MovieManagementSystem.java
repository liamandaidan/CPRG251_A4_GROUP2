/**
 * 
 */
package sait.mms.managers;

import java.sql.*;
import java.time.*;
import java.util.*;
import sait.mms.drivers.MariaDBDriver;
import sait.mms.problemdomain.Movie;

/**
 * This class will manage the movie system. It also controls the display.
 * 
 * @author Benson, Liam, Robyn, Mike
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
	 * Displays the menu for the application.
	 * 
	 * @author Robyn
	 * @throws SQLException Throws an SQL Exception.
	 */

	public void displayMenu() throws SQLException {

		String title;
		int id, duration, year;

		int option;
		boolean valid = false;

		in = new Scanner(System.in);

		do {

			System.out.printf("\nJim's Movie Manager\n" + "1. Add New Movie\n" + "2. Print movies released in year\n"
					+ "3. Print random list of movies\n" + "4. Delete a movie\n" + "5. Exit\n\n");

			System.out.printf("Enter option: ");

			try {
				option = in.nextInt();
				in.nextLine(); // flush the line
				switch (option) {
				case 1:
					System.out.print("\nEnter movie title: ");
					title = in.nextLine();
					System.out.print("\nEnter duration: ");
					duration = in.nextInt();
					System.out.print("\nEnter year: ");
					year = in.nextInt();
					addMovie(duration, title, year);
					break;
				case 2:
					System.out.printf("\nEnter in year: ");
					year = in.nextInt();
					printMoviesInYear(year);
					break;
				case 3:
					System.out.print("\nEnter number of movies: ");
					int numMovies = in.nextInt();
					if (numMovies <= 0) {
						throw new Exception("\nPlease enter a valid integer above 0.");
					}
					printRandomMovies(numMovies);
					break;
				case 4:
					System.out.printf("\nEnter the movie ID you want to delete: ");
					id = in.nextInt();
					deleteMovie(id);
					break;
				case 5:
					valid = true;
					System.out.println("\nGoodbye!\n");
					break;
				default:
					System.out.printf("\nPlease check your input and try again\n");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Please check your input and try again\n");
				in.next();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} while (!valid);

		// exit
		md.disconnect();
		in.close();

	}

	/**
	 * Adds a movie into the database.
	 * 
	 * @param id    The movie id.
	 * @param dur   The movie duration.
	 * @param title The movie title.
	 * @param yr    The year the movie released.
	 * @throws SQLException Throws an SQL Exception.
	 */
	public void addMovie(int dur, String title, int yr) throws SQLException {
		Movie film = new Movie(dur, title, yr);
		String sqlStatement = "INSERT INTO movies(duration, title, year) VALUES(" + film.getDuration() + ",'"
				+ film.getTitle() + "'," + film.getYear() + ");";
		int rows = md.update(sqlStatement);
		System.out.println("Added movie to database.\n");

	}

	/**
	 * Prints out all of the movies released in a specific year.
	 * 
	 * @param yr the year to display.
	 * @throws SQLException Throws an SQL Exception.
	 */
	public void printMoviesInYear(int yr) throws SQLException {
		String sqlStatement = "SELECT * FROM movies WHERE year = " + yr + ";";
		ResultSet result = md.get(sqlStatement);
		int counter = 0;
		String f = String.format("\nMovie List\n%-8s\t%4s\t%-255s\n", "Duration", "Year", "Title");
		// String f = String.format("%-8s\t%4s\t%-255s\n", "Duration", "Year", "Title");
		while (result.next()) {
			f += String.format("%-8s\t%4s\t%-255s\n", result.getInt(2), result.getInt(4), result.getString(3));
			counter += result.getInt(2);
		}
		if (counter == 0)
			System.out.println("\nNo Movie Found\nPlease Seach For Another Year\n");
		else
			System.out.println(f + "\nTotal duration: " + counter + " minutes\n");

	}

	/**
	 * Prints out a random number of movies.
	 * 
	 * @param numOfMovies The number of movies you want in a list.
	 * @throws SQLException Throws an SQL Exception.
	 */
	public void printRandomMovies(int numOfMovies) throws SQLException {
		// Step 1 select how many movies there are total
		String sqlStmt = "SELECT COUNT(id) FROM movies";
		String sqlInp = "SELECT * FROM movies";
		// Step 2 select a random movie
		int duration, year;
		String title;
		System.out.println("\nMovie List");
		int durCount = 0;
		String f = String.format("%-8s\t%4s\t%-255s\n", "Duration", "Year", "Title");
		for (int h = 0; h < numOfMovies; h++) {// repeat how ever many movies we look for
			ResultSet randNum = md.get(sqlStmt);
			randNum.next();
			int rand = (int) (randNum.getInt(1) * Math.random());
			ResultSet result = md.get(sqlInp);
			for (int i = 1; i <= rand && result.next(); i++) {
				// loop through movies until we reach a random index. Since the ID could be
				// deleted.
				if (i == rand) {
					duration = result.getInt(2);
					title = result.getString(3);
					year = result.getInt(4);
					f += String.format("%-8s\t%4s\t%-255s\n", duration, year, title);
					durCount += duration;
				}

			}
		}

		System.out.println(f + "\nTotal duration: " + durCount + " minutes\n\n");

	}

	/**
	 * Deletes a movie based on the ID of the movie.
	 * 
	 * @param movieID The ID of the movie.
	 */
	public void deleteMovie(int movieId) {

		try {
			String checkStmt = String.format("SELECT * FROM movies WHERE id = %s", movieId);
			ResultSet chkResult = md.get(checkStmt);
			if (chkResult.next()) {
				String sqlStmt = String.format("DELETE FROM movies WHERE id = %s", movieId);
				int rows = md.update(sqlStmt);
				System.out.println("\nMovie " + movieId + " is deleted.\n");
			} else {
				System.out.println("I'm sorry Dave, I'm afraid I can't do that.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
