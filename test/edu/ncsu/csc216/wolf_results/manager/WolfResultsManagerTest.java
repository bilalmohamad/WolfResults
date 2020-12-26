package edu.ncsu.csc216.wolf_results.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests WolfResultsManager.
 * 
 * @author Marwah Mahate
 * @author Bilal Mohamad
 *
 */
public class WolfResultsManagerTest {

	/** WolfResultsManager that is observed by the test class. */
	private WolfResultsManager m;

	/** Boolean flag to let us know if update() has been called */
	private boolean updated;
	/**
	 * Reference to Observable passed to update(). That way we can check that the
	 * right object was passed to update().
	 */
	private Object updatedObject;

	/**
	 * Set up method. Adds an observer to the instance under test.
	 * @throws Exception if there is any exception in the setUp
	 */
	@Before
	public void setUp() throws Exception {
		// Create a new WolfResultsManager
		m = WolfResultsManager.getInstance();

		// Adds a new Observer to the WolfResultsManager
		m.addObserver(new Observer() {

			/**
			 * If the Observable object calls notifyObservers(), this update() method will
			 * be called. It will set the updated flag to true and assign the second
			 * argument, obj, to the updatedObject. This is the Object that was passed into
			 * the notifyObservers() call by the Observable object.
			 * 
			 * For this project, every call to notifyObservers() should pass in the current
			 * instance. Then we can check that the current instance is correct!
			 */
			@Override
			public void update(Observable o, Object obj) {
				updated = true;
				updatedObject = obj;
			}

		});
	}

	/**
	 * Tests update WolfResultsManager.
	 */
	@Test
	public void testWolfResultsManagerObserver() {
		// Always set updated/updatedObject to false/null BEFORE calling a
		// method that should notifyObservers(). This way you can make sure that
		// the checks are testing the right thing!
		updated = false;
		updatedObject = null;

		assertEquals("Checks initial race list", 0, m.getRaceList().size());

		m.getRaceList().addRace("marathon", 26, LocalDate.of(2018, 11, 26), "Raleigh, NC");
		assertTrue(m.isChanged());

		// Check that the turnCount has been updated
		assertEquals("Checks updated race list", 1, m.getRaceList().size());

		// Check that the Observer.update() method defined in the test has been
		// called. If so, updated is true!
		assertTrue("Observers should be notified after adding race," + " but they were not.", updated);
		// Check that the correct object was passed to Observer.update()
		assertEquals("Observers should be notified after adding race," + " but they were not.", "marathon",
				((WolfResultsManager) updatedObject).getRaceList().getRace(0).getName());

		// Reset our flags
		updated = false;
		updatedObject = null;
	}
	
	/**
	 * Tests load method.
	 */
	@Test
	public void testLoad() {
		m.setFilename("test-files/sample.md");
		assertEquals("test-files/sample.md", m.getFilename());
		
		m.loadFile("test-files/sample.md");
		assertEquals(2, m.getRaceList().size());
		
		try {
			m.setFilename("        ");
			fail();
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}
		
		try {
			m.loadFile(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}
		
		m.saveFile("test-files/my_sample.md");
		checkFiles("test-files/sample.md", "test-files/my_sample.md");
		
		m.newList();
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

}
