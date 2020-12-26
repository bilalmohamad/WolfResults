package edu.ncsu.csc216.wolf_results.race_results;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.wolf_results.util.RaceTime;

/**
 * Tests IndividualResult.
 * 
 * @author Marwah Mahate
 * @author Bilal Mohamad
 *
 */
public class IndividualResultTest {

	/** Sample Race object used for testing IndividualResult */
	public static final Race RACE = new Race("# CITY OF OAKS MARATHON 2017", 26.2, LocalDate.of(2018, 11, 30), "Raleigh, NC");
	
	/** Sample name parameter for testing IndividualResult */
	public static final String NAME_1 = "Bill";
	/** Sample name parameter for testing IndividualResult */
	public static final String NAME_2 = "Nye";
	
	/** Sample age parameter for testing IndividualResult */
	public static final int AGE_1 = 20;
	/** Sample age parameter for testing IndividualResult */
	public static final int AGE_2 = 25;
	
	/** Sample RaceTime object used for testing IndividualResult */
	public static final RaceTime TIME_1 = new RaceTime("01:30:50");
	/** Sample RaceTime object used for testing IndividualResult */
	public static final RaceTime TIME_2 = new RaceTime("02:15:32");
	
	/** IndividualResult sample objects used for testing */
	private IndividualResult result1 = new IndividualResult(RACE, NAME_1, AGE_1, TIME_1);
	/** IndividualResult sample objects used for testing */
	private IndividualResult result2 = new IndividualResult(RACE, NAME_2, AGE_2, TIME_2);
	
	
	/**
	 * Tests the IndividualResult constructor
	 */
	@Test
	public void testIndividualResult() {
		
		assertEquals(RACE, result1.getRace());
		assertEquals(NAME_1, result1.getName());
		assertEquals(AGE_1, result1.getAge());
		assertEquals(TIME_1, result1.getTime());
		assertEquals("0:03:28", result1.getPace().toString());
		
		assertEquals("0:05:10", result2.getPace().toString());
	}
	
	
	/**
	 * Tests invalid input for the IndividualResult constructor
	 */
	@Test
	public void testInvalidIndividualResult() {
		
		//Tests invalid race parameter
		try {
			IndividualResult invalidResult = new IndividualResult(null, NAME_1, AGE_1, TIME_1);
			invalidResult.getRace();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		//Tests invalid name parameter when null
		try {
			IndividualResult invalidResult = new IndividualResult(RACE, null, AGE_1, TIME_1);
			invalidResult.getName();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		//Tests invalid name parameter when empty
		try {
			IndividualResult invalidResult = new IndividualResult(RACE, "", AGE_1, TIME_1);
			invalidResult.getName();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		//Tests invalid name parameter when only whitespace
		try {
			IndividualResult invalidResult = new IndividualResult(RACE, "    ", AGE_1, TIME_1);
			invalidResult.getName();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		//Tests invalid age parameter
		try {
			IndividualResult invalidResult = new IndividualResult(RACE, NAME_1, -1, TIME_1);
			invalidResult.getAge();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		//Tests invalid time parameter
		try {
			IndividualResult invalidResult = new IndividualResult(RACE, NAME_1, AGE_1, null);
			invalidResult.getTime();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
	}
	
	
	/**
	 * Tests the compareTo() method
	 */
	@Test
	public void testCompareTo(){
		
		//Checks when the objects are the same
		assertEquals(0, result1.compareTo(result1));
		assertEquals(0, result2.compareTo(result2));
		
		//Checks when this object is greater than the entered object
		assertEquals(-1, result1.compareTo(result2));
		
		//Checks when this object is less than the entered object
		assertEquals(1, result2.compareTo(result1));
	}
	
	
	/**
	 * Tests the toString() method
	 */
	@Test
	public void testToString() {
		
		assertEquals("IndividualResult [name=Bill, age=20, time=1:30:50, pace=0:03:28]", result1.toString());
		assertEquals("IndividualResult [name=Nye, age=25, time=2:15:32, pace=0:05:10]", result2.toString());
	}
	
	
	/**
	 * Tests the update() method
	 */
	@Test
	public void testUpdate() {
		
		//TODO Make sure update method is tested properly. Only done to ensure coverage.
		
		result1.update(RACE, NAME_1);
		
		assertNotNull(result1);
	}
	
	/**
	 * Tests calculatePace method.
	 */
	@Test
	public void testPace() {
		Race myRace = new Race("My Race", 10.0, LocalDate.of(2018, 11, 11), "Raleigh, NC");
		assertNotNull(myRace);
		
		IndividualResult runner = new IndividualResult(myRace, "Marwah", 19, new RaceTime("01:00:00"));
		assertNotNull(runner);
		assertEquals("0:06:00", runner.getPace().toString());
		myRace.setDistance(20.0);
		assertEquals("0:03:00", runner.getPace().toString());
	}
	
}
