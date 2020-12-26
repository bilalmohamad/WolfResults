package edu.ncsu.csc216.wolf_results.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_results.race_results.IndividualResult;
import edu.ncsu.csc216.wolf_results.race_results.Race;
import edu.ncsu.csc216.wolf_results.race_results.RaceList;
import edu.ncsu.csc216.wolf_results.util.RaceTime;

/**
 * This class will be used to open and read in a .md file to create a RaceList
 * for further actions.
 * 
 * @author Bilal Mohamad
 * @author Marwah Mahate
 */
public class WolfResultsReader {

	/**
	 * The constructor for the WolfResultsReader class. Used to create
	 * WolfResultsReader objects
	 */
	public WolfResultsReader() {
		// empty
	}

	/*private static void printFile(String fileName) {
		try {
			FileInputStream file = new FileInputStream(fileName);
			Scanner fileScanner = new Scanner(file);
			int line = 1;
			System.out.println("Begin " + fileName);
			while (fileScanner.hasNextLine()) {
				System.out.format("%3d:[%s]\n", line, fileScanner.nextLine());
				line++;
			}
			System.out.println("End " + fileName);
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error file does not exist " + fileName);
		}
	}*/

	/**
	 * Creates a RaceList from the entered file name. Returns the RaceList created
	 * from the read in file.
	 * 
	 * @param fileName The fileName to be used for processing
	 * @return the RaceList made from the entered file
	 */
	public static RaceList readRaceListFile(String fileName) {
		// Debug print the file
//		printFile(fileName);

		RaceList raceList = new RaceList();

		FileInputStream file;

		try {
			file = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}

		Scanner fileScanner = new Scanner(file);
		int state = 0;
		Race currentRace = null;
		String raceName = null;
		double distance = 0.0;
		LocalDate date = null;
		String location = null;
		int lines = 0;

		// System.out.println("Begin file " + fileName);

		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine().trim();
			// System.out.println(line);

			if (state == 0) {
				if (line.startsWith("# ") && line.length() > 2) {
					state = 1;
					raceName = line.substring(2);
					lines = 0;
					continue;
				} else {
					fileScanner.close();
					throw new IllegalArgumentException();
				}
				// continue;
			} else if (state == 1) {
				switch (lines) {
				case 0:
					try {
						distance = Double.parseDouble(line);
						lines++;
					} catch (NumberFormatException e) {
						fileScanner.close();
						throw new IllegalArgumentException(e);
					}
					break;
				case 1:
					try {
						date = LocalDate.parse(line);
						lines++;
					} catch (DateTimeParseException e) {
						fileScanner.close();
						throw new IllegalArgumentException(e);
					}
					break;
				case 2:
					location = line;
					lines++;
					break;
				case 3:
					if (line.isEmpty()) {
						state = 2;
						lines = 0;
						currentRace = new Race(raceName, distance, date, location);
						raceList.addRace(currentRace);

						// clear data for next race
						raceName = null;
						distance = 0.0;
						date = null;
						location = null;
					} else {
						fileScanner.close();
						throw new IllegalArgumentException();
					}
					break;
				default:
					break;
				}
				continue;
			} else if (state == 2) {
				if (line.startsWith("* ") && line.length() > 2) {
					Scanner lineScanner = new Scanner(line.substring(2));
					lineScanner.useDelimiter(",");
					String runnerName = null;
					int age = 0;
					String runnerTime = null;

					try {
						runnerName = lineScanner.next();
						age = lineScanner.nextInt();
						runnerTime = lineScanner.next();
					} catch (NoSuchElementException e) {
						lineScanner.close();
						fileScanner.close();
						throw new IllegalArgumentException(e);
					}

					IndividualResult result = new IndividualResult(currentRace, runnerName, age,
							new RaceTime(runnerTime));
					currentRace.addIndividualResult(result);
					lineScanner.close();
					continue;
				} else if (line.trim().isEmpty()) {
					state = 0;
					// clear data for next race
					currentRace = null;
					continue;
				} else {
					fileScanner.close();
					throw new IllegalArgumentException();
				}
			}
		}
		// System.out.println("End of file " + fileName);
		fileScanner.close();


		return raceList;
	}

}
