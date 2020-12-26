package edu.ncsu.csc216.wolf_results.util;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * RaceTime represents a race time in the following format: hh:mm:ss, where hours may be listed as one digit or 
 * many digits with leading zeros and minutes and seconds are always two digits between 0 and 59 (inclusive).
 * 
 * @author Bilal Mohamad
 * @author Marwah Mahate
 */
public class RaceTime {
	
	/** Constant for converting hours to seconds */
	public static final int HOURS_TO_SECONDS = 3600;
	
	/** Constant for converting minutes to seconds */
	public static final int MINUTES_TO_SECONDS = 60;

	/** The race time's hours */
	private int hours;
	
	/** The race time's minutes */
	private int minutes;
	
	/** The race time's seconds */
	private int seconds;
	
	
	/**
	 * Constructs a RaceTime object using 3 integers: hours, minutes, and seconds.
	 * 
	 * @param hours		The hours of the race time
	 * @param minutes	The minutes of the race time
	 * @param seconds	The seconds of the race time
	 * 
	 * @throws IllegalArgumentException if hours is negative or minutes or seconds are not in between 0-59 (inclusive)
	 */
	public RaceTime(int hours, int minutes, int seconds) {
		
		//Checks invalid hours
		if (hours < 0) {
			throw new IllegalArgumentException("Invalid time");
		}
		
		//Checks invalid minutes
		if (minutes < 0 || minutes > 59) {
			throw new IllegalArgumentException("Invalid time");
		}
		
		//Checks invalid seconds
		if (seconds < 0 || seconds > 59) {
			throw new IllegalArgumentException("Invalid time");
		}
		
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	
	/**
	 * Constructs a RaceTime object using a String of the time in the format hh:mm:ss
	 * 
	 * @param time	a string of the race time in the format hh:mm:ss
	 * @throws IllegalArgumentException if time is not in the correct format (hh:mm:ss), hh is negative, or mm or ss are not in between 0 and 59 (inclusive)
	 */
	public RaceTime(String time) {
		Scanner scan = new Scanner(time);
		scan.useDelimiter(":");
		
		int h;
		int m;
		int s;
		
		try {
			h = scan.nextInt();
			m = scan.nextInt();
			s = scan.nextInt();
		}
		catch (NoSuchElementException e) {
			scan.close();
			throw new IllegalArgumentException("Incorrect format");
		}
		
		//Checks for correct formatting. If there is any other information, it is invalid.
		if (scan.hasNext()) {
			scan.close();
			throw new IllegalArgumentException("Incorrect format");
		}
		
		//Checks invalid hours
		if (h < 0) {
			scan.close();
			throw new IllegalArgumentException("Invalid time");
		}
		
		//Checks invalid minutes
		if (m < 0 || m > 59) {
			scan.close();
			throw new IllegalArgumentException("Invalid time");
		}
		
		//Checks invalid seconds
		if (s < 0 || s > 59) {
			scan.close();
			throw new IllegalArgumentException("Invalid time");
		}
		
		scan.close();
		
		this.hours = h;
		this.minutes = m;
		this.seconds = s;
	}
	
	
	/**
	 * Retrieves the race time's hours
	 * 
	 * @return	the race time's hours
	 */
	public int getHours() {
		return hours;
	}
	
	
	/**
	 * Retrieves the race time's minutes
	 * 
	 * @return	the race time's minutes
	 */
	public int getMinutes() {
		return minutes;
	}
	
	
	/**
	 * Retrieves the race time's seconds
	 * 
	 * @return	the race time's seconds
	 */
	public int getSeconds() {
		return seconds;
	}
	
	
	/**
	 * Converts the race time to seconds
	 * 
	 * @return	the race time in seconds
	 */
	public int getTimeInSeconds() {
		return hours * HOURS_TO_SECONDS + minutes * MINUTES_TO_SECONDS + seconds;
	}
	
	
	/**
	 * Converts the race time into a String in format: 
	 * hh:mm:ss
	 * 
	 * @return the race time converted into the format of hours:minutes:seconds
	 */
	public String toString() {
		
		String s = hours + ":";
		
		if (minutes < 10) {
			s += "0";
		}
		s += minutes + ":";
		
		
		if (seconds < 10) {
			s += "0";
		}
		s += seconds;
		
		
		return s;
	}
	
	
	/**
	 * Compares different race times based on their total time
	 * 
	 * @param other	the other RaceTime used for comparison
	 * 
	 * @return 	0	if the race times are equal
	 * 			1	if this race time is greater than the parameter race time
	 * 			-1	if this race time is less than the parameter race time
	 */
	public int compareTo(RaceTime other) {
		
		if (this.getTimeInSeconds() == other.getTimeInSeconds()) {
			return 0;
		}
		else if (this.getTimeInSeconds() > other.getTimeInSeconds()) {
			return 1;
		}
		else {
			return -1;
		}
	}
}
