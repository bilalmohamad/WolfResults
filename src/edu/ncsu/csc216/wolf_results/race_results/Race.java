package edu.ncsu.csc216.wolf_results.race_results;

import java.time.LocalDate;
import java.util.Observable;

import edu.ncsu.csc216.wolf_results.util.RaceTime;

/**
 * A representation of a Race in the WolfResultsManager application.
 * 
 * @author Bilal Mohamad
 * @author Marwah Mahate
 */
public class Race extends Observable {

	/** The name of the race */
	private String name;
	
	/** The distance of the race */
	private double distance;
	
	/** The date of the race */
	private LocalDate date;
	
	/** The location of the race */
	private String location;
	
	/** The list of results of the race */
	private RaceResultList results;
	
	
	/**
	 * The constructor used for creating a Race object. The results instance variable will be set to the existing entered results.
	 * 
	 * @param name		the name of the race
	 * @param distance	the distance of the race
	 * @param date		the date of the race
	 * @param location	the location of the race
	 * @param results	the list of results of the race
	 * 
	 * @throws IllegalArgumentException if 	name is null, empty, or all whitespace
	 * 										distance is non-positive
	 * 										date is null
	 * 										location is null
	 * 										results is null
	 */
	public Race(String name, double distance, LocalDate date, String location, RaceResultList results) {
		
		if (name == null || name.isEmpty() || name.trim().length() == 0) {
			throw new IllegalArgumentException();
		}
		
		if (distance <= 0) {
			throw new IllegalArgumentException();
		}
		
		if (date == null) {
			throw new IllegalArgumentException();
		}
		
		if (location == null) {
			throw new IllegalArgumentException();
		}
		
		if (results == null) {
			throw new IllegalArgumentException();
		}
		
		
		this.name = name.trim();
		this.distance = distance;
		this.date = date;
		this.location = location.trim();
		this.results = results;
	}
	
	
	/**
	 * The constructor used for creating a Race object. The results instance variable will be set to a new RaceResultList
	 * 
	 * @param name		the name of the race
	 * @param distance	the distance of the race
	 * @param date		the date of the race
	 * @param location	the location of the race
	 * 
	 * @throws IllegalArgumentException if 	name is empty, null, or all whitespace
	 * 										distance is non-positive
	 * 										date is null
	 * 										location is null
	 */
	public Race(String name, double distance, LocalDate date, String location) {
		this(name, distance, date, location, new RaceResultList());
	}
	
	
	/**
	 * Retrieves the name of the race
	 * @return the name of the race
	 */
	public String getName() {
		return name;
	}
	

	/**
	 * Retrieves the distance of the race
	 * @return the distance of the race
	 */
	public double getDistance() {
		return distance;
	}
	
	
	/**
	 * Retrieves the date of the race
	 * @return the date of the race
	 */
	public LocalDate getDate() {
		return date;
	}
	
	
	/**
	 * Retrieves the location of the race
	 * @return the location of the race
	 */
	public String getLocation() {
		return location;
	}
	
	
	/**
	 * Retrieves the list of results of the race
	 * @return the list of results of the race
	 */
	public RaceResultList getResults() {
		return results;
	}
	
	
	/**
	 * Adds an IndividualResult to the RaceResultList. Observers of Race are notified of the change.
	 * 
	 * @param result the IndividualResult to be added to the list
	 */
	public void addIndividualResult(IndividualResult result) {
		results.addResult(result);
		
		setChanged();
		notifyObservers(this);
//		result.update(this.ob, arg);
		
		//TODO Make sure Observer notification was done right
	}
	
	
	/**
	 * Sets the distance to the entered distance. Observers of Race are notified of the change.
	 * 
	 * @param distance the value to set the distance to
	 * @throws IllegalArgumentException if the distance is non-positive
	 */
	public void setDistance(double distance) {
		
		if (distance < 0) {
			throw new IllegalArgumentException();
		}
		
		this.distance = distance;
		setChanged();
		notifyObservers(this);
		//TODO Make sure Observer notification was done right
	}
	
	
	/**
	 * Creates a RaceResultList of all the runners that are in between the minAge and maxAge range and the minPace and maxPace range.
	 * 
	 * @param minAge	the lower bound of the age range
	 * @param maxAge	the upper bound of the age range
	 * @param minPace	the lower bound of the pace range
	 * @param maxPace	the upper bound of the pace range
	 * 
	 * @return a list of the results such that the runner's age is between minAge and maxAge (inclusive) and pace is between minPace and maxPace (inclusive)
	 */
	public RaceResultList filter(int minAge, int maxAge, String minPace, String maxPace) {
//		return results.filter(minAge, maxAge, minPace, maxPace);
		
		RaceResultList filterList = new RaceResultList();
		
		for (int i = 0; i < results.size(); i++) {
			
			//Converts the pace ranges into numbers that can be used for comparison
			int min = new RaceTime(minPace).getTimeInSeconds();
			int max = new RaceTime(maxPace).getTimeInSeconds();
			
			//Checks if the result meets the specified ranges
			if ((results.getResult(i).getAge() >= minAge && results.getResult(i).getAge() <= maxAge)
					&& (results.getResult(i).getPace().getTimeInSeconds() >= min && results.getResult(i).getPace().getTimeInSeconds() <= max)) {
				
				filterList.addResult(results.getResult(i));
			}
		}
		return filterList;
	}


	/**
	 * The string representation of a Race is the name, distance, date, and location fields in the format:
	 * NAME (DISTANCE miles) on DATE at LOCATION
	 * 
	 * @return the string representation of a race
	 */
	@Override
	public String toString() {
		return this.name + " (" + this.distance + " miles) on " + this.date + " at " + this.location;
	}


	/**
	 * Hashes the entered data using the hash algorithm.
	 * Based on the name, distance, date, and location fields.
	 * 
	 * @return the hashed information
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		long temp;
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	/**
	 * Compares the current object with the entered object. If they are equals then it will return true otherwise false.
	 * Based on the name, distance, date, and location fields.
	 * 
	 * @param obj	the object used for comparison
	 * 
	 * @return	true	if the current object and the entered object are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Race other = (Race) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
