package edu.ncsu.csc216.wolf_results.race_results;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.wolf_results.util.RaceTime;

/**
 * Tests for Race class. Demonstrates testing an Observable object.
 * 
 * @author Jessica Young Schmidt
 * @author Bilal Mohamad
 * @author Marwah Mahate
 */
public class RaceTest {
	
	/** Sample Race object used for testing IndividualResult */
	public static final Race RACE = new Race("One 5k", 3.1, LocalDate.of(2018, 11, 30), "NCSU");
	
	/** Sample Race object used for testing IndividualResult */
	public static final Race RACE_2 = new Race("# CITY OF OAKS MARATHON 2017", 26.2, LocalDate.of(2018, 11, 30), "Raleigh, NC");
	
	/** Sample name parameter for testing IndividualResult */
	public static final String NAME_1 = "Bill";

	/** Sample name parameter for testing IndividualResult */
	public static final String NAME_2 = "Nye";

	/** Sample age parameter for testing IndividualResult */
	public static final int AGE_1 = 20;

	/** Sample age parameter for testing IndividualResult */
	public static final int AGE_2 = 25;

	/** Sample RaceTime object used for testing IndividualResult */
	public static final RaceTime TIME_1 = new RaceTime("00:18:20");
	/** Sample RaceTime object used for testing IndividualResult */
	public static final RaceTime TIME_2 = new RaceTime("00:21:32");

	/** Sample IndividualResult objects used for testing */
	public static final IndividualResult RESULT_0 = new IndividualResult(RACE, "Quan", 47, new RaceTime("00:21:36"));
	/** Sample IndividualResult objects used for testing */
	public static final IndividualResult RESULT_1 = new IndividualResult(RACE, "John", 26, new RaceTime("00:48:39"));
	/** Sample IndividualResult objects used for testing */
	public static final IndividualResult RESULT_2 = new IndividualResult(RACE, "Eric", 52, new RaceTime("00:15:18"));
	/** Sample IndividualResult objects used for testing */
	public static final IndividualResult RESULT_3 = new IndividualResult(RACE, NAME_1, AGE_1, TIME_1);
	/** Sample IndividualResult objects used for testing */
	public static final IndividualResult RESULT_4 = new IndividualResult(RACE, NAME_2, AGE_2, TIME_2);
	
    /** Delta for testing doubles */
    public static final double DELTA = 0.0001;

    /** Race that is observed by the test class. */
    private Race r;
    /** Boolean flag to let us know if update() has been called */
    private boolean updated;
    /**
     * Reference to Observable passed to update(). That way we can check that
     * the right object was passed to update().
     */
    private Object updatedObject;

    /**
     * Set up method. Adds an observer to the instance under test.
     * @throws Exception if there is any exception in the setUp
     */
    @Before
    public void setUp() throws Exception {
        // Create a new Race
        r = new Race("One 5k", 3.1, LocalDate.of(2018, 11, 30), "NCSU");

        // Adds a new Observer to the Race
        r.addObserver(new Observer() {

            /**
             * If the Observable object calls notifyObservers(), this update()
             * method will be called. It will set the updated flag to true and
             * assign the second argument, obj, to the updatedObject. This is
             * the Object that was passed into the notifyObservers() call by the
             * Observable object.
             * 
             * For this project, every call to notifyObservers() should pass in
             * the current instance. Then we can check that the current instance
             * is correct!
             */
            @Override
            public void update(Observable o, Object obj) {
                updated = true;
                updatedObject = obj;
            }

        });
    }

    /**
     * Tests update distance.
     */
    @Test
    public void testSetDistanceObserver() {
        // Always set updated/updatedObject to false/null BEFORE calling a
        // method that should notifyObservers(). This way you can make sure that
        // the checks are testing the right thing!
        updated = false;
        updatedObject = null;

        assertEquals("Checks initial distance", 3.1, r.getDistance(), DELTA);

        // Race.setDistance() should call setChanged; notifiyObservers(); if the
        // count is incremented.
        r.setDistance(5);
        // Check that the turnCount has been updated
        assertEquals("Updating distance from 3.1 to 5.0", 5.0, r.getDistance(),
                DELTA);
        // Check that the Observer.update() method defined in the test has been
        // called. If so, updated is true!
        assertTrue("Observers should be notified after updating distance,"
                + " but they were not.", updated);
        // Check that the correct object was passed to Observer.update()
        assertEquals(
                "Observers should be notified after updating distance,"
                        + " but they were not.",
                "One 5k", ((Race) updatedObject).getName());

        // Reset our flags
        updated = false;
        updatedObject = null;

        // Race.setDistance() should call setChanged; notifiyObservers(); if the
        // count is incremented.
        r.setDistance(15.5);
        // Check that the turnCount has been updated
        assertEquals("Updating distance from 5.0 to 15.5", 15.5,
                r.getDistance(), DELTA);
        // Check that the Observer.update() method defined in the test has been
        // called. If so, updated is true!
        assertTrue("Observers should be notified after updating distance,"
                + " but they were not.", updated);
        // Check that the correct object was passed to Observer.update()
        assertEquals(
                "Observers should be notified after updating distance,"
                        + " but they were not.",
                "One 5k", ((Race) updatedObject).getName());
    }
    
    
    /**
     * Test invalid parameter for setDistance() method
     */
    @Test
    public void testInvalidSetParameter() {
    	
    	try {
    		RACE.setDistance(-1);
    		fail();
    	}
    	catch (IllegalArgumentException e) {
    		assertNull(e.getMessage());
    	}
    }

    
    /**
     * Tests the Race constructor and the getter methods of the Race class
     */
	@Test
    public void testRace() {
    	
    	assertEquals("One 5k", r.getName());
    	assertEquals(3.1, r.getDistance(), DELTA);
    	assertEquals(LocalDate.of(2018, 11, 30), r.getDate());
    	assertEquals("NCSU", r.getLocation());
    	assertEquals(0, r.getResults().size());
    }
	
	
	/**
	 * Tests the Race constructor using invalid parameters
	 */
	@Test
	public void testInvalidRace() {
		
		//Tests invalid name parameter when null
		try {
			Race invalidRace = new Race(null, 3.1, LocalDate.of(2018, 11, 30), "NCSU");
			invalidRace.getName();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		//Tests invalid name parameter when empty
		try {
			Race invalidRace = new Race("", 3.1, LocalDate.of(2018, 11, 30), "NCSU");
			invalidRace.getName();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		//Tests invalid name parameter when whitespace
		try {
			Race invalidRace = new Race("     ", 3.1, LocalDate.of(2018, 11, 30), "NCSU");
			invalidRace.getName();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		//Tests invalid distance parameter
		try {
			Race invalidRace = new Race("One 5k", -1, LocalDate.of(2018, 11, 30), "NCSU");
			invalidRace.getDistance();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		//Tests invalid date parameter
		try {
			Race invalidRace = new Race("One 5k", 3.1, null, "NCSU");
			invalidRace.getDate();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		//Tests invalid location parameter
		try {
			Race invalidRace = new Race("One 5k", 3.1, LocalDate.of(2018, 11, 30), null);
			invalidRace.getDistance();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		//Tests invalid results parameter
		try {
			Race invalidRace = new Race("One 5k", 3.1, LocalDate.of(2018, 11, 30), "NCSU", null);
			invalidRace.getDistance();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
	}
	
	
	/**
	 * Tests the addIndividualResult() method
	 */
	@Test
	public void testAddIndividualResult() {
			
		r.addIndividualResult(RESULT_0);
		r.addIndividualResult(RESULT_1);
		r.addIndividualResult(RESULT_2);
		r.addIndividualResult(RESULT_3);
		r.addIndividualResult(RESULT_4);
		
		assertEquals(5, r.getResults().size());
		assertEquals(RESULT_2, r.getResults().getResult(0));
		assertEquals(RESULT_3, r.getResults().getResult(1));
		assertEquals(RESULT_4, r.getResults().getResult(2));
		assertEquals(RESULT_0, r.getResults().getResult(3));
		assertEquals(RESULT_1, r.getResults().getResult(4));
	}
	
	
	/**
	 * Tests the filter() method
	 */
	@Test
	public void testFilter() {
		
		assertEquals(0, r.getResults().size());
		
		r.addIndividualResult(RESULT_0);
		r.addIndividualResult(RESULT_1);
		r.addIndividualResult(RESULT_2);
		r.addIndividualResult(RESULT_3);
		r.addIndividualResult(RESULT_4);
		
		assertEquals(RESULT_3, r.filter(15, 25, "00:04:30", "00:08:00").getResult(0));
		assertEquals(RESULT_4, r.filter(15, 25, "00:04:30", "00:08:00").getResult(1));
		assertEquals(2, r.filter(15, 25, "00:04:30", "00:08:00").size());
	}
	
	
	/**
	 * Tests the toString() method
	 */
	@Test
	public void testToString() {
		
		assertEquals("One 5k (3.1 miles) on 2018-11-30 at NCSU", RACE.toString());
	}
	
	
	/**
	 * Tests the hashCode() method
	 */
	@Test
	public void testHashCode() {
		
		assertNotNull(RACE.hashCode());
	}
	
	
	/**
	 * Tests the equals() method
	 */
	@Test
	public void testEquals() {
		
		assertTrue(RACE.equals(RACE));
		
		assertFalse(RACE == null);
		
		assertFalse(RACE.equals(RACE_2));
		assertFalse(RACE.equals(new Race("One 5k", 3.1, LocalDate.of(2018, 11, 30), "UNC")));
		assertFalse(RACE.equals(new Race("One 5k", 3.0, LocalDate.of(2018, 11, 30), "NCSU")));
	}
}