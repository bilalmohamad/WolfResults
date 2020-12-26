package edu.ncsu.csc216.wolf_results.manager;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.wolf_results.model.io.WolfResultsReader;
import edu.ncsu.csc216.wolf_results.model.io.WolfResultsWriter;
import edu.ncsu.csc216.wolf_results.race_results.RaceList;

/**
 * Maintains the data structures for the entire application.
 * 
 * @author Marwah Mahate
 * @author Bilal Mohamad
 *
 */
public class WolfResultsManager extends Observable implements Observer {

	/**
	 * Singleton WolfResults Manager.
	 */
	private static WolfResultsManager singleton;

	/**
	 * Name of the file
	 */
	private String filename;

	/**
	 * Indicator for determining whether there any change has been made to the current instance
	 */
	private boolean changed = false;

	/**
	 * The list containing the list of races
	 */
	private RaceList list;

	/**
	 * Constructor for WolfResultsManager. Notifies observers.
	 */
	private WolfResultsManager() {
		this.list = new RaceList();
		this.list.addObserver(this);
		notifyObservers(this);
	}

	/**
	 * Creates a new RaceList and notifies observers of the change
	 */
	public void newList() {
		this.list = new RaceList();
		this.list.addObserver(this);
		notifyObservers(this);
		setChanged(false);
	}

	/**
	 * Retrieves the changed value depending on whether any changes have been made to the current instance
	 * @return the change instance variable's value
	 */
	public boolean isChanged() {
		return this.changed;
	}

	/**
	 * Sets the change instance variable to the entered parameter depending on whether changes were made to the current instance
	 * @param changed the value to set the change instance variable to
	 */
	private void setChanged(boolean changed) {
		this.changed = changed;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Retrieves the current file name
	 * @return the name of the file
	 */
	public String getFilename() {
		return this.filename;
	}

	/**
	 * Sets the fileName instance variable to the entered parameter
	 * @param fileName the name of the file to set the current fileName instance variable to
	 */
	public void setFilename(String fileName) {
		if (fileName == null || fileName.trim().isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.filename = fileName.trim();
	}

	/**
	 * Reads in the entered file and adds the information to the list of races
	 * @param fileName the name of the file to retreive information from
	 */
	public void loadFile(String fileName) {
		try {
			this.setFilename(fileName);
			this.list = WolfResultsReader.readRaceListFile(filename);
			this.list.addObserver(this);
			// this.changed = false;
			// notifyObservers(this);
			setChanged(true);
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}

	/**
	 * Saves the current file to entered file name
	 * @param fileName the name of the new saved file
	 */
	public void saveFile(String fileName) {
		// fix TS test
		WolfResultsWriter.writeRaceFile(fileName, list);
		this.changed = false;
	}

	/**
	 * Retrieves the current list of races
	 * @return list current list of races
	 */
	public RaceList getRaceList() {
		return this.list;
	}

	/**
	 * Updates information any current changes
	 * 
	 * @param obs Observable	the object being observed
	 * @param obj Object		the object that contains the new change to be updated
	 */
	public void update(Observable obs, Object obj) {
		setChanged(true);
	}

	/**
	 * Retrieves the current instance of WolfResultsManager.
	 * 
	 * @return singleton the current instance of WolfResultsManager
	 */
	public static WolfResultsManager getInstance() {
		if (WolfResultsManager.singleton == null) {
			WolfResultsManager.singleton = new WolfResultsManager();
		}
		return WolfResultsManager.singleton;
	}
}
