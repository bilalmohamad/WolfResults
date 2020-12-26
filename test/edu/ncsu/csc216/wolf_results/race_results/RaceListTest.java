package edu.ncsu.csc216.wolf_results.race_results;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Test;

/**
 * Tests the RaceList class
 * 
 * @author Marwah Mahate
 * @author Bilal Mohamad
 *
 */
public class RaceListTest {

	/** Sample name parameter for testing Race */
	public static final String NAME_1 = "Big Race";
	/** Sample name parameter for testing Race */
	public static final String NAME_2 = "Turkey Trot";
	
	/** Sample distance parameter for testing Race */
	public static final double DISTANCE_1 = 13.1;
	/** Sample distance parameter for testing Race */
	public static final double DISTANCE_2 = 3.1;

	/** Sample LocalDate parameter for testing Race */
	public static final LocalDate DATE_1 = LocalDate.of(2017, 1, 1);
	/** Sample LocalDate parameter for testing Race */
	public static final LocalDate DATE_2 = LocalDate.of(2018, 11, 22);

	/** Sample location parameter for testing Race */
	public static final String LOCATION_1 = "Harvard University";
	/** Sample location parameter for testing Race */
	public static final String LOCATION_2 = "Downtown Raleigh";
	
	
	/** Sample Race object used for testing Race */
	public static final Race RACE_1 = new Race("One 5k", 3.1, LocalDate.of(2018, 11, 30), "NCSU");
	/** Sample Race object used for testing Race */
	public static final Race RACE_2 = new Race("# CITY OF OAKS MARATHON 2017", 26.2, LocalDate.of(2018, 11, 30), "Raleigh, NC");
	/** Sample Race object used for testing Race */
	public static final Race RACE_3 = new Race(NAME_1, DISTANCE_1, DATE_1, LOCATION_1);
	/** Sample Race object used for testing Race */
	public static final Race RACE_4 = new Race(NAME_2, DISTANCE_2, DATE_2, LOCATION_2);

	/**
	 * Tests the addRace with a Race object as the parameter
	 */
	@Test
	public void testAddRaceWithRaceParameter() {
		
		RaceList rl = new RaceList();
		assertEquals(0, rl.size());
		
		rl.addRace(RACE_1);
		assertEquals(1, rl.size());
		
		rl.addRace(RACE_2);
		assertEquals(2, rl.size());
		
		assertEquals(RACE_1, rl.getRace(0));
		assertEquals(RACE_2, rl.getRace(1));
	}
	
	
	/**
	 * Tests the addRace method with an invalid parameter
	 */
	@Test
	public void testInvalidAddRace() {
		
		try {
			RaceList rl = new RaceList();
			rl.addRace(null);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
	}
	
	
	/**
	 * Tests the addRace with all the parameters needed to construct a Race object
	 */
	@Test
	public void testAddRaceWithSeveralParameters() {
		
		RaceList rl = new RaceList();
		assertEquals(0, rl.size());
		
		rl.addRace(NAME_1, DISTANCE_1, DATE_1, LOCATION_1);
		assertEquals(1, rl.size());
		
		rl.addRace(NAME_2, DISTANCE_2, DATE_2, LOCATION_2);
		assertEquals(2, rl.size());
		
		assertEquals(RACE_3, rl.getRace(0));
		assertEquals(RACE_4, rl.getRace(1));
	}
	
	
	/**
	 * Tests the removeRace method
	 */
	@Test
	public void testRemoveRace() {
		
		RaceList rl = new RaceList();
		assertEquals(0, rl.size());
		
		rl.addRace(RACE_1);
		rl.addRace(RACE_2);
		rl.addRace(RACE_3);
		rl.addRace(RACE_4);
		
		assertEquals(4, rl.size());
		
		rl.removeRace(1);
		rl.removeRace(1);
		
		assertEquals(RACE_1, rl.getRace(0));
		assertEquals(RACE_4, rl.getRace(1));
		
	}
	
	
	/**
	 * Tests the update method
	 */
	@Test
	public void testUpdate() {
		
		RaceList rl = new RaceList();
		assertEquals(0, rl.size());
		
		//TODO Make sure update method is tested properly. Only done to ensure coverage.
		rl.update(RACE_1, RACE_2);
		
	}
}
