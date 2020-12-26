package edu.ncsu.csc216.wolf_results.race_results;

import edu.ncsu.csc216.wolf_results.util.RaceTime;
import edu.ncsu.csc216.wolf_results.util.SortedLinkedList;

/**
 * An ParkList has a SortedLinkedList of IndividualResult.
 * 
 * @author Marwah Mahate
 * @author Bilal Mohamad
 *
 */
public class RaceResultList {
	
	/** Constant for the number of columns in the array */
	public static final int COLUMNS = 4;
	
	/** A SortedLinkedList containing individual results of the racers */
	private SortedLinkedList<IndividualResult> results;

	/**
	 * Constructor for RaceResultList.
	 */
	public RaceResultList() {
		this.results = new SortedLinkedList<IndividualResult>();
	}
	
	/**
	 * Adds results to the SortedLinkedList
	 * 
	 * @param result the IndividualResult to add to the SortedLinkedList
	 * 
	 * @throws IllegalArgumentException	if the parameter result is null
	 */
	public void addResult(IndividualResult result) {
		if (result == null) {
			throw new IllegalArgumentException();
		}
		
		this.results.add(result);
	}
	
	/**
	 * Adds results to the SortedLinkedList
	 * 
	 * @param race	The race of the runner participated in
	 * @param name	The name of the runner
	 * @param age	The age of the runner
	 * @param time	The time the racer achieved in the race
	 */
	public void addResult(Race race, String name, int age, RaceTime time) {
		IndividualResult result = new IndividualResult(race, name, age, time);
		addResult(result);
	}
	
	/**
	 * Retrieves the IndividualResult at the specified index in the SortedLinkedList
	 * 
	 * @param index 	index of the item to be retrieved
	 * @return results	the IndividualResults at the specified index
	 */
	public IndividualResult getResult(int index) {
		if (index < 0 || index >= results.size()) {
			throw new IndexOutOfBoundsException();
		}
		return this.results.get(index);
	}
	
	/**
	 * Size of the list of SortedLinkedList results
	 * @return the size of the results
	 */
	public int size() {
		return this.results.size();
	}
	
	/**
	 * If the list is empty, an empty 2D array is returned.
	 * Converts the SortedLinkedList results into an array in the format:
	 * [0]name  [1]age  [2]time  [3]pace
	 * 
	 * @return a 2D array of the SortedLinkedList results
	 */
	public String[][] getResultsAsArray() {
		String[][] resultArray = new String[results.size()][COLUMNS];
		
		for (int i = 0; i < results.size(); i++) {
			resultArray[i][0] = results.get(i).getName();
			resultArray[i][1] = results.get(i).getAge() + "";
			resultArray[i][2] = results.get(i).getTime().toString();
			resultArray[i][3] = results.get(i).getPace().toString();
		}
		
		return resultArray;
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
		
		RaceResultList filterList = new RaceResultList();
		
		for (int i = 0; i < results.size(); i++) {
			
			//Converts the pace ranges into numbers that can be used for comparison
			int min = new RaceTime(minPace).getTimeInSeconds();
			int max = new RaceTime(maxPace).getTimeInSeconds();
						
			//Checks if the result meets the specified ranges
			if ((results.get(i).getAge() >= minAge && results.get(i).getAge() <= maxAge)
					&& (results.get(i).getPace().getTimeInSeconds() >= min && results.get(i).getPace().getTimeInSeconds() <= max)) {
				
				filterList.addResult(results.get(i));
			}
		}
		return filterList;
	}
}
