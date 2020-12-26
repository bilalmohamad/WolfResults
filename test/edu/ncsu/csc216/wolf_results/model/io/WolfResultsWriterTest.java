package edu.ncsu.csc216.wolf_results.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.wolf_results.race_results.RaceList;

/**
 * Tests WolfResultsWriter.
 * 
 * @author Marwah Mahate
 * @author Bilal Mohamad
 * @author Sarah Heckman
 *
 */
public class WolfResultsWriterTest {
	
	/**
	 * Tests the save method.
	 */
	@Test
	public void testSave() {
		RaceList raceList = null;
		try {
			raceList = WolfResultsReader.readRaceListFile("test-files/sample.md");
			assertNotNull(raceList);
			assertEquals(2, raceList.size());
			assertEquals(25, raceList.getRace(0).getResults().size());
			assertEquals(10, raceList.getRace(1).getResults().size());
			// CITY OF OAKS MARATHON 2017, 26.2, 2017-11-06, Raleigh, NC
			assertEquals("CITY OF OAKS MARATHON 2017", raceList.getRace(0).getName());
			assertEquals(26.2, raceList.getRace(0).getDistance(), 0.01);
			assertEquals("2017-11-06", raceList.getRace(0).getDate().toString());
			assertEquals("Raleigh, NC", raceList.getRace(0).getLocation());
			
			//9/11 Memorial Run at NC State, 2.02, 2018-09-08, Raleigh, NC
			assertEquals("9/11 Memorial Run at NC State", raceList.getRace(1).getName());
			assertEquals(2.02, raceList.getRace(1).getDistance(), 0.01);
			assertEquals("2018-09-08", raceList.getRace(1).getDate().toString());
			assertEquals("Raleigh, NC", raceList.getRace(1).getLocation());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			WolfResultsWriter.writeRaceFile("test-files/my_sample.md", raceList);
			checkFiles("test-files/sample.md", "test-files/my_sample.md");
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * Copied from Lab StudentDirectoryTest
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	/**
	 * Tests the WolfResultsWriter constructor.
	 */
	@Test
	public void testCtor() {
		WolfResultsWriter w = new WolfResultsWriter();
		assertNotNull(w);
	}
	
}
