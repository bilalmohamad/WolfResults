package edu.ncsu.csc216.wolf_results.race_results;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.wolf_results.util.RaceTime;

/**
 * A representation of one person�s race result for a single race in the WolfResultsManager application.
 * 
 * @author Bilal Mohamad
 * @author Marwah Mahate
 */
public class IndividualResult implements Observer, Comparable<IndividualResult> {

	/** Constant for converting hours to seconds */
	public static final int HOURS_TO_SECONDS = 3600;
	
	/** Constant for converting minutes to seconds */
	public static final int MINUTES_TO_SECONDS = 60;

	/** The race the racer is participating in */
	private Race race;
	
	/** The name of individual racer */
	private String name;
	
	/** The age of individual racer */
	private int age;
	
	/** The race time of individual racer */
	private RaceTime time;
	
	/** The pace of individual racer */
	private RaceTime pace;
	
	
	/**
	 * The constructor used for creating IndividualResult objects
	 * 
	 * @param race	the race the racer participated in
	 * @param name	the name of the racer
	 * @param age	the name of the race
	 * @param time	the time the racer took to complete the race
	 * 
	 * @throws IllegalArgumentException if 	race is null
	 * 										name is null, empty string, or all whitespace
	 * 										age is negative
	 * 										time is null
	 */
	public IndividualResult(Race race, String name, int age, RaceTime time) {
		
		if (race == null) {
			throw new IllegalArgumentException();
		}
		
		if (name == null || name.isEmpty() || name.trim().length() == 0) {
			throw new IllegalArgumentException();
		}
		
		if (age < 0) {
			throw new IllegalArgumentException();
		}
		
		if (time ==  null) {
			throw new IllegalArgumentException();
		}
		
		this.race = race;
		this.name = name.trim();
		this.age = age;
		this.time = time;
		
		this.pace = calculatePace();
		
		race.addObserver(this);
	}
	
	
	/**
	 * Retrieves the race the racer participated in
	 * @return	the race the racer participated in
	 */
	public Race getRace() {
		return race;
	}
	

	/**
	 * Retrieves the name of the racer
	 * @return the name of the racer
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Retrieves the age of the racer
	 * @return the age of the racer
	 */
	public int getAge() {
		return age;
	}
	
	
	/**
	 * Retrieves the time the racer took to complete the race
	 * @return the time of the racer to complete the race
	 */
	public RaceTime getTime() {
		return time;
	}
	
	
	/**
	 * Retrieves the pace of the racer
	 * @return the pace of the racer
	 */
	public RaceTime getPace() {
		return pace;
	}
	
	
	/**
	 * Compares two IndividualResult objects based on their time
	 * 
	 * @param other the second IndividualResult object used for comparison
	 * 
	 * @return 	0	if the race times are equal
	 * 			1	if this race time is greater than the parameter race time
	 * 			-1	if this race time is less than the parameter race time
	 */
	public int compareTo(IndividualResult other) {
		return time.compareTo(other.getTime());
	}
	
	
	/**
	 * Converts the IndividualResult into the following String format:
	 * IndividualResult [name=NAME, age=AGE, time=TIME, pace=PACE]
	 * 
	 * @return String
	 */
	public String toString() {
		return "IndividualResult [name=" + name + ", age=" + age + ", time=" + time.toString() + ", pace=" + pace.toString() + "]";
	}
	
	
	/**
	 * Updates the pace if the Race that the IndividualResult is observing notified its observers of a change
	 * 
	 * @param o		the variable used for attaching and detaching Observers to the client that will be observed
	 * @param arg	the value being used to update the current argument to
	 */
	//TODO Double-check Javadoc of entire method
	public void update(Observable o, Object arg) {
		// o is the race object that has been changed and we have to calculatePace
		// arg is the argument passed in addObserver which is this object
		this.pace = this.calculatePace();
		// o.notifyObservers(arg);
		//TODO Double-check if even done correctly.
	}
	

	/**
	 * Helper method for calculating the pace runner's race based on their time and the distance ran
	 * 
	 * @param race	The race was being ran. Used to obtain the race's distance
	 * @param time	The time the racer took to complete the race.
	 * 
	 * @return	The pace of the racer
	 */
	private RaceTime calculatePace() {
		double calcPace = this.time.getTimeInSeconds() / this.race.getDistance();
		int hours = Math.floorDiv((int) calcPace, HOURS_TO_SECONDS);
		calcPace -= hours * HOURS_TO_SECONDS;
		int minutes = Math.floorDiv((int) calcPace, MINUTES_TO_SECONDS);
		calcPace -= minutes * MINUTES_TO_SECONDS;
		int seconds = (int) calcPace;
		
		return new RaceTime(hours, minutes, seconds);
	}
}
