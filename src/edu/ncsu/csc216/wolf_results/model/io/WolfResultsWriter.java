package edu.ncsu.csc216.wolf_results.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import edu.ncsu.csc216.wolf_results.race_results.IndividualResult;
import edu.ncsu.csc216.wolf_results.race_results.Race;
import edu.ncsu.csc216.wolf_results.race_results.RaceList;

/**
 * This class will create a .md file with the entered file name and RaceList
 * 
 * @author Bilal Mohamad
 * @author Marwah Mahate
 */
public class WolfResultsWriter {

	/**
	 * The constructor for the WolfResultsWriter class. Used to create
	 * WolfResultsWriter objects
	 */
	public WolfResultsWriter() {
		// write later.
	}

	/**
	 * Writes the given RaceList to the given file.
	 * 
	 * @param fileName The file name of the new file to be created
	 * @param list     The RaceList to create the file from.
	 * 
	 * @throws IllegalArgumentException if the there is any error
	 */
	public static void writeRaceFile(String fileName, RaceList list) {
		
		if (list == null || fileName == null || fileName.isEmpty()) {
			return;
		}
		
		PrintStream fileWriter;

		try {
			fileWriter = new PrintStream(new File(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		}

		for (int i = 0; i < list.size(); i++) {
			Race race = list.getRace(i);
			fileWriter.println("# " + race.getName());
			fileWriter.println(race.getDistance());
			fileWriter.println(race.getDate().toString());
			fileWriter.println(race.getLocation());
			fileWriter.println();

			for (int j = 0; j < race.getResults().size(); j++) {
				IndividualResult result = race.getResults().getResult(j);
				fileWriter.format("* %s,%d,%s\n", result.getName(), result.getAge(), result.getTime().toString());
			}
			fileWriter.println();

		}

		fileWriter.close();
	}
}
