package edu.ncsu.csc216.wolf_results.race_results;

import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.wolf_results.util.ArrayList;

/**
 * This class uses an ArrayList of Race objects for adding, removing, or the retrieval of a particular race.
 * 
 * @author Marwah Mahate
 * @author Bilal Mohamad
 *
 */
public class RaceList extends Observable implements Observer {
	
	/** ArrayList of Race objects */
	private ArrayList races;

	
	/**
	 * Constructor for for the RaceList class
	 */
	public RaceList() {
		this.races = new ArrayList();
		notifyObservers(this);
	}
	
	
	/**
	 * Adds races to the ArrayList when the entered parameter is a Race object
	 * 
	 * @param race	the Race object to be added to the list of races
	 * 
	 * @throws IllegalArgumentException if the race parameter is null.
	 */
	public void addRace(Race race) {	
		if (race == null) {
			throw new IllegalArgumentException();
		}
		
		this.races.add(race);
		
		// Add RaceList as observer of the newly added race
		race.addObserver(this);
		
		// Notify all observers of RaceList that it has been changed
		setChanged();
		notifyObservers(this);
	}
	
	
	/**
	 * Adds races using the each of the parameters that make up a Race object
	 * 
	 * @param name		the name of the race
	 * @param distance	the distance of the race
	 * @param date		the date of the race
	 * @param location	the location of the race
	 */
	public void addRace(String name, double distance, LocalDate date, String location) {
		this.addRace(new Race(name, distance, date, location));
	}
	
	
	/**
	 * Removes a race from the list of races at the specified index
	 * 
	 * @param index the index of the race of the list of races
	 */
	public void removeRace(int index) {
		this.races.remove(index);
		
		// Notify all observers of RaceList that it has been changed
		setChanged();
		notifyObservers(this);
	}
	
	
	/**
	 * Retrieves the a race from the list of races at the specified index
	 * 
	 * @param index the index of the race of the list of races
	 * 
	 * @return the Race object at the specified index
	 */
	public Race getRace(int index) {
		return (Race) this.races.get(index);
	}
	
	
	/**
	 * Retrieves the size of the ArrayList containing the list of all the races
	 * 
	 * @return the size of the races list
	 */
	public int size() {
		return this.races.size();
	}
	
	
	/**
	 * Update is called when Race objects in the list are updated
	 * 
	 * @param obs Observable
	 * @param obj Object
	 */
	public void update(Observable obs, Object obj) {
		setChanged();
		notifyObservers(obj);
	}
}
