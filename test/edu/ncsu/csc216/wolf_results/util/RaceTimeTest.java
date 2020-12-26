package edu.ncsu.csc216.wolf_results.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests RaceTime.
 * 
 * @author Marwah Mahate
 * @author Bilal Mohamad
 *
 */
public class RaceTimeTest {
	
	/** Constant for converting hours to seconds */
	public static final int HOURS_TO_SECONDS = 3600;
	
	/** Constant for converting minutes to seconds */
	public static final int MINUTES_TO_SECONDS = 60;
	
	/** Sample times used for testing */
	public static final int HOURS = 2;
	/** Sample times used for testing */
	public static final int MINUTES = 9;
	/** Sample times used for testing */
	public static final int SECONDS = 5;
	/** Sample times used for testing */
	public static final String TIME_FORMAT = "1:43:19";
	
	/** Sample RaceTime object used for testing */
	private RaceTime rt = new RaceTime(HOURS, MINUTES, SECONDS);
	
	/** Sample RaceTime object created from a String*/
	private RaceTime rtString = new RaceTime(TIME_FORMAT);
	
	
	/**
	 * Tests the RaceTime constructor using 3 integers
	 */
	@Test
	public void testRaceTime() {
		
		assertEquals(HOURS, rt.getHours());
		assertEquals(MINUTES, rt.getMinutes());
		assertEquals(SECONDS, rt.getSeconds());
	}
	
	
	/**
	 * Tests the RaceTime constructor using 3 integers that would cause errors
	 */
	@Test
	public void testInvalidRaceTime() {
		
		//Tests invalid hours
		try {
			RaceTime rt1 = new RaceTime(-1, MINUTES, SECONDS);
			rt1.getHours();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Invalid time", e.getMessage());
		}
		
		//Tests invalid minutes upper bound
		try {
			RaceTime rt1 = new RaceTime(HOURS, 60, SECONDS);
			rt1.getMinutes();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Invalid time", e.getMessage());
		}
		
		//Tests invalid minutes lower bound
		try {
			RaceTime rt1 = new RaceTime(HOURS, -1, SECONDS);
			rt1.getMinutes();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Invalid time", e.getMessage());
		}
		
		//Tests invalid seconds upper bound
		try {
			RaceTime rt1 = new RaceTime(HOURS, MINUTES, 60);
			rt1.getSeconds();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Invalid time", e.getMessage());
		}
		
		//Tests invalid seconds lower bound
		try {
			RaceTime rt1 = new RaceTime(HOURS, MINUTES, -1);
			rt1.getSeconds();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Invalid time", e.getMessage());
		}
	}
	
	
	/**
	 * Tests the RaceTime constructor using a String
	 */
	@Test
	public void testRaceTimeString() {
		
		assertEquals(1, rtString.getHours());
		assertEquals(43, rtString.getMinutes());
		assertEquals(19, rtString.getSeconds());
	}
	
	
	/**
	 * Tests the RaceTime constructor using an invalid String
	 */
	@Test
	public void testInvalidRaceTimeString() {
		
		try {
			RaceTime rt1 = new RaceTime("1::20");
			rt1.getTimeInSeconds();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Incorrect format", e.getMessage());
		}
		
		try {
			RaceTime rt1 = new RaceTime("1:05:20:5");
			rt1.getTimeInSeconds();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Incorrect format", e.getMessage());
		}
		
		
		//Tests invalid hours
		try {
			RaceTime rt1 = new RaceTime("-1:15:10");
			rt1.getHours();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Invalid time", e.getMessage());
		}
		
		//Tests invalid minutes upper bound
		try {
			RaceTime rt1 = new RaceTime("0:60:15");
			rt1.getMinutes();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Invalid time", e.getMessage());
		}
		
		//Tests invalid minutes lower bound
		try {
			RaceTime rt1 = new RaceTime("1:-1:48");
			rt1.getMinutes();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Invalid time", e.getMessage());
		}
		
		//Tests invalid seconds upper bound
		try {
			RaceTime rt1 = new RaceTime("3:56:60");
			rt1.getSeconds();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Invalid time", e.getMessage());
		}
		
		//Tests invalid seconds lower bound
		try {
			RaceTime rt1 = new RaceTime("1:28:-1");
			rt1.getSeconds();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Invalid time", e.getMessage());
		}
	}
	
	
	/**
	 * Tests the getTimeInSeconds() method
	 */
	@Test
	public void testGetTimeInSeconds() {
		
		assertEquals(HOURS * HOURS_TO_SECONDS + MINUTES * MINUTES_TO_SECONDS + SECONDS, rt.getTimeInSeconds());
		assertEquals(1 * HOURS_TO_SECONDS + 43 * MINUTES_TO_SECONDS + 19, rtString.getTimeInSeconds());
	}
	
	
	/**
	 * Tests the toString() method
	 */
	@Test
	public void testToString() {
		
		assertEquals("2:09:05", rt.toString());
		assertEquals(TIME_FORMAT, rtString.toString());
	}
	
	
	/**
	 * Tests the compareTo() method
	 */
	@Test
	public void testCompareTo() {
		
		//Checks when the objects are the same
		assertEquals(0, rt.compareTo(rt));
		assertEquals(0, rtString.compareTo(rtString));
		
		//Checks when this object is greater than the entered object
		assertEquals(1, rt.compareTo(rtString));
		
		//Checks when this object is less than the entered object
		assertEquals(-1, rtString.compareTo(rt));
	}
}
