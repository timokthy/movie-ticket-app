package model;

/**
 * The ShowTime class that represents a movie showing
 * @author Timothy Mok
 *
 */
public class ShowTime {

	private int scheduleId;
	private String title;
	private String dayShowing;
	private String timeslot;
	private String auditorium;
	private String seats;
	
	/**
	 * Constructs a showtime
	 * @param scheduleId the ID of the showtime in the database
	 * @param title
	 * @param dayShowing
	 * @param timeslot
	 * @param auditorium
	 * @param seats
	 */
	public ShowTime(int scheduleId, String title, String dayShowing, String timeslot,
			String auditorium, String seats){
		this.setScheduleId(scheduleId);
		this.title = title;
		this.setDayShowing(dayShowing);
		this.setTimeslot(timeslot);
		this.setAuditorium(auditorium);
		this.setSeats(seats);
	}

	/**
	 * Gets the title of the movie
	 * @return the movie title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the movie
	 * @param movieName
	 */
	public void setTitle(String movieName) {
		this.title = movieName;
	}

	/**
	 * Gets the ID of the showtime
	 * @return the showtime ID
	 */
	public int getScheduleId() {
		return scheduleId;
	}

	/**
	 * Sets the showtime ID in the database
	 * @param scheduleId
	 */
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	/**
	 * Gets the day of the week of the showtime
	 * @return a day of the week
	 */
	public String getDayShowing() {
		return dayShowing;
	}

	/**
	 * Sets the day of the week of the showing
	 * @param dayShowing
	 */
	public void setDayShowing(String dayShowing) {
		this.dayShowing = dayShowing;
	}

	/**
	 * Gets the time of the showing
	 * @return a time
	 */
	public String getTimeslot() {
		return timeslot;
	}

	/**
	 * Sets the time of the showing
	 * @param timeslot
	 */
	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}

	/**
	 * Gets the auditorium the showing is displaying in
	 * @return
	 */
	public String getAuditorium() {
		return auditorium;
	}

	/**
	 * Sets the auditorium the movie is showing in
	 * @param auditorium
	 */
	public void setAuditorium(String auditorium) {
		this.auditorium = auditorium;
	}

	/**
	 * Gets the seating arrangement of the showtime
	 * @return the taken seats of the showtime
	 */
	public String getSeats() {
		return seats;
	}

	/**
	 * Updates the seating chart after a seat is taken
	 * @param seats
	 */
	public void setSeats(String seats) {
		this.seats = seats;
	}

}

