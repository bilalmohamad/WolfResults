package edu.ncsu.csc216.wolf_results.race_results;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.wolf_results.util.RaceTime;

/**
 * Tests RaceResultList.
 * 
 * @author Marwah Mahate
 * @author Bilal Mohamad
 *
 */
public class RaceResultListTest {

	/** Sample Race object used for testing IndividualResult */
	public static final Race RACE = new Race("# CITY OF OAKS MARATHON 2017", 26.2, LocalDate.MAX, "Raleigh, NC");
	
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

	/** Sample IndividualResult objects used for testing */
	public static final IndividualResult RESULT_0 = new IndividualResult(RACE, "Quan", 47, new RaceTime("03:21:32"));
	/** Sample IndividualResult objects used for testing */
	public static final IndividualResult RESULT_1 = new IndividualResult(RACE, "John", 26, new RaceTime("02:48:39"));
	/** Sample IndividualResult objects used for testing */
	public static final IndividualResult RESULT_2 = new IndividualResult(RACE, "Eric", 52, new RaceTime("04:02:18"));
	/** Sample IndividualResult objects used for testing */
	public static final IndividualResult RESULT_3 = new IndividualResult(RACE, NAME_1, AGE_1, TIME_1);
	/** Sample IndividualResult objects used for testing */
	public static final IndividualResult RESULT_4 = new IndividualResult(RACE, NAME_2, AGE_2, TIME_2);
	
	
	/**
	 * Tests the addResult method with using an IndividualResult object as the parameter
	 */
	@Test
	public void testAddResultWithIndividualResultParameter() {
		
		RaceResultList resultList = new RaceResultList();
		
		assertEquals(0, resultList.size());
		
		resultList.addResult(RESULT_0);
		resultList.addResult(RESULT_1);
		resultList.addResult(RESULT_2);
		
		assertEquals(3, resultList.size());
		assertEquals(RESULT_1, resultList.getResult(0));
		assertEquals(RESULT_0, resultList.getResult(1));
		assertEquals(RESULT_2, resultList.getResult(2));
	}
	
	
	/**
	 * Tests the addResult method with using an IndividualResult object as the parameter
	 */
	@Test
	public void testAddResultWithFourParameters() {
		
		RaceResultList resultList = new RaceResultList();
		
		assertEquals(0, resultList.size());
		
		resultList.addResult(RACE, NAME_1, AGE_1, TIME_1);
		resultList.addResult(RACE, NAME_2, AGE_2, TIME_2);
		
		assertEquals(2, resultList.size());
		assertEquals(RESULT_3.toString(), resultList.getResult(0).toString());
		assertEquals(RESULT_4.toString(), resultList.getResult(1).toString());
	}
	
	
	/**
	 * Tests the addResult method with an invalid parameter
	 */
	@Test
	public void testInvalidAddResult() {
		
		try {
			RaceResultList invalidResultList = new RaceResultList();
			invalidResultList.addResult(null);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
	}
	
	
	/**
	 * Tests the getResult method with an invalid parameter
	 */
	@Test
	public void testInvalidGetResult() {
		
		RaceResultList invalidResultList = new RaceResultList();
		
		invalidResultList.addResult(RESULT_0);
		invalidResultList.addResult(RESULT_1);
		invalidResultList.addResult(RESULT_2);
		
		//Tests below the range of the list
		try {
			invalidResultList.getResult(-1);
			fail();
		}
		catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
		
		//Tests above the range of the list
		try {
			invalidResultList.getResult(3);
			fail();
		}
		catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
	}
	
	
	/**
	 * Tests the getResultsArray method
	 */
	@Test
	public void testGetResultsArray() {
		
		String[][] testArray = new String[3][4];
		
		testArray[0][0] = RESULT_1.getName();
		testArray[0][1] = RESULT_1.getAge() + "";
		testArray[0][2] = RESULT_1.getTime().toString();
		testArray[0][3] = RESULT_1.getPace().toString();
		
		testArray[1][0] = RESULT_0.getName();
		testArray[1][1] = RESULT_0.getAge() + "";
		testArray[1][2] = RESULT_0.getTime().toString();
		testArray[1][3] = RESULT_0.getPace().toString();
		
		testArray[2][0] = RESULT_2.getName();
		testArray[2][1] = RESULT_2.getAge() + "";
		testArray[2][2] = RESULT_2.getTime().toString();
		testArray[2][3] = RESULT_2.getPace().toString();
		
		
		RaceResultList resultList = new RaceResultList();
		
		resultList.addResult(RESULT_1);
		resultList.addResult(RESULT_0);
		resultList.addResult(RESULT_2);
		
		for (int i = 0; i < 3; i++) {
			assertEquals(testArray[i][0], resultList.getResultsAsArray()[i][0]);
			assertEquals(testArray[i][1], resultList.getResultsAsArray()[i][1]);
			assertEquals(testArray[i][2], resultList.getResultsAsArray()[i][2]);
			assertEquals(testArray[i][3], resultList.getResultsAsArray()[i][3]);
		}
	}
	
	
	/**
	 * Tests the filter method
	 */
	@Test
	public void testFilter() {
		
		RaceResultList resultList = new RaceResultList();
		
		resultList.addResult(RESULT_0);
		resultList.addResult(RESULT_1);
		resultList.addResult(RESULT_2);
		resultList.addResult(RESULT_3);
		resultList.addResult(RESULT_4);
		
		RaceResultList filterList = resultList.filter(20, 25, "0:03:00", "0:07:00");
		
		assertEquals(RESULT_3, filterList.getResult(0));
		assertEquals(RESULT_4, filterList.getResult(1));
	}
}
