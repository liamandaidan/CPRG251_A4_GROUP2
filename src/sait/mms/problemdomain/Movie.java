/**
 * 
 */
package sait.mms.problemdomain;

import java.io.Serializable;

/**
 * Creation of the movie object.
 * @author Mikepcann
 *
 */
public class Movie  implements Serializable{
	
	static final long serialVersionUID = 2L;
	
	private int id;
	private int duration;
	private String title;
	private int year;
	
	/**
	 *  No arg default constructor. 
	 */
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a movie object with constructors for the fields.
	 * @param id Id number of the movie.
	 * @param duration The duration of the movie.
	 * @param title The title of the movie.
	 * @param year The year the movie was released.
	 */
	public Movie(int id, int duration, String title, int year) {
		super();
		this.id = id;
		this.duration = duration;
		this.title = title;
		this.year = year;
	}
	
	/**
	 * Creates a movie object with constructors for the fields.
	 * @param duration The duration of the movie.
	 * @param title The title of the movie.
	 * @param year The year the movie was released.
	 */
	public Movie(int duration, String title, int year) {
		super();
		this.duration = duration;
		this.title = title;
		this.year = year;
	}

	/** 
	 * Gets the ID of the movie.
	 * @return the id.
	 */
	public int getId() {
		return id;
	}

	/** 
	 * Sets the ID of the movie.
	 * @param id the id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the duration of the movie.
	 * @return the duration.
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets the duration of the movie.
	 * @param duration the duration to set.
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Gets the title of the movie.
	 * @return the title of the movie.
	 */
	public String getTitle() {
		return title;
	}

	/** 
	 * Sets the title of the movie.
	 * @param title the title of the movie.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the year the movie was published.
	 * @return the year the movie was published.
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the year the movie was published.
	 * @param year The year the movie was published. 
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Returns the movie object as a formatted String.
	 * @return The movie object represented as a string.
	 */
	@Override
	public String toString() {
		return "Movie [id=" + id + ", duration=" + duration + ", title=" + title + ", year=" + year + "]";
	}
}
