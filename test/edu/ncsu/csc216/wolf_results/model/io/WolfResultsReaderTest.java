package edu.ncsu.csc216.wolf_results.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.ncsu.csc216.wolf_results.race_results.RaceList;

/**
 * Tests WolfResultsReader.
 * 
 * @author Marwah Mahate
 * @author Bilal Mohamad
 *
 */
public class WolfResultsReaderTest {

	/**
	 * Tests an invalid file of races.
	 */
	@Test
	public void testInvalidFile() {
		try {
			WolfResultsReader.readRaceListFile("file.md");
			fail();
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}
	}
	/**
	 * Tests WolfResultsReader constructor.
	 */
	@Test
	public void testCtor() {
		WolfResultsReader r = new WolfResultsReader();
		assertNotNull(r);
	}
	
	/**
	 * Tests sample.md file.
	 */
	@Test
	public void testSample() {
		try {
			RaceList raceList = WolfResultsReader.readRaceListFile("test-files/sample.md");
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
	}
	
	/**
	 * Tests another invalid file.
	 */
	@Test
	public void testIllegal() {
		try {
			WolfResultsReader.readRaceListFile("test-files/invalidfile.md");
			fail();
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}
		
		try {
			WolfResultsReader.readRaceListFile("test-files/invaliddate.md");
			fail();
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}
		
		try {
			WolfResultsReader.readRaceListFile("test-files/missingline.md");
			fail();
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}
		
		try {
			WolfResultsReader.readRaceListFile("test-files/raceresultsinvalid.md");
			fail();
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}
	}
	
	/**
	 * Tests a file of four races.
	 */
	@Test
	public void testRaces() {
		RaceList raceList = WolfResultsReader.readRaceListFile("test-files/just_races.md");
		assertNotNull(raceList);
		assertEquals(4, raceList.size());
		assertEquals(4, raceList.getRace(0).getResults().size());
		assertEquals(4, raceList.getRace(2).getResults().size());
	}
	
	/**
	 * Tests a file with one race.
	 */
	@Test
	public void testOneRace() {
		RaceList raceList = WolfResultsReader.readRaceListFile("test-files/one_race.md");
		assertNotNull(raceList);
		assertEquals(1, raceList.size());
	}

	/**
	 * Tests a file with missing blank lines.
	 */
	@Test
	public void testMissingBlankLines() {
		
		try{
			WolfResultsReader.readRaceListFile("test-files/missing_blank_lines.md");
			fail();
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}
		
		try{
			WolfResultsReader.readRaceListFile("test-files/missing_blank_lines2.md");
			fail();
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}
	}
	
//	/**
//	 * Tests the ts_race_results.md file.
//	 */
//	@Test
//	public void testTSRaceResults() {
//		RaceList raceList = WolfResultsReader.readRaceListFile("test-files/ts_race_results.md");
//		assertNotNull(raceList);
//		assertEquals(2, raceList.size());
//		assertEquals(2, raceList.getRace(0).getResults().size());
//		assertEquals(1, raceList.getRace(1).getResults().size());
//		
//		Race race = raceList.getRace(0);
//		assertNotNull(race);
//		
//		// * JACOB WORMALD (# 1292),22,00:31:26
//		// * TIMOTHY RYAN (# 1010),28,00:31:49
//		race.addIndividualResult(new IndividualResult(race, "JACOB WORMALD (# 1292)", 22, new RaceTime("00:31:26")));
//		race.addIndividualResult(new IndividualResult(race, "TIMOTHY RYAN (# 1010)", 28, new RaceTime("00:31:49")));
//		assertEquals(4, raceList.getRace(0).getResults().size());
//		
//		RaceList raceList2 = WolfResultsReader.readRaceListFile("test-files/ts-test-files/race_results.md");
//		assertNotNull(raceList2);
//		assertEquals(2, raceList2.size());
//		assertEquals(4, raceList2.getRace(0).getResults().size());
//		assertEquals(4, raceList2.getRace(1).getResults().size());
//		
//	}
}
