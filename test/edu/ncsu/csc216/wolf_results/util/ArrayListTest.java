package edu.ncsu.csc216.wolf_results.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests ArrayList.
 * 
 * @author Marwah Mahate
 * @author Bilal Mohamad
 *
 */
public class ArrayListTest {
	
	/**
	 * Tests a list of words and the removal and addition in the list.
	 */
	@Test
	public void testNames() {
		String[] names = { "USA", "India", "Angola", "Spain", "Canada" };
		
		ArrayList list = new ArrayList(2);
		assertNotNull(list);
		assertTrue(list.isEmpty());
		
		for (int i = 0; i < names.length; i++) {
			list.add(names[i]);
			assertEquals(i + 1, list.size());
			assertEquals(names[i], list.get(i));
			assertTrue(list.contains(names[i]));
			assertEquals(i, list.indexOf(names[i]));
			assertFalse(list.contains("hello"));
		}
		
		assertFalse(list.isEmpty());
		
		list.add(5, "Zimbabwe");
		assertEquals(6, list.size());
		
		for (int i = 0; i < names.length; i++) {
			assertEquals(names[i], list.get(i));
			assertTrue(list.contains(names[i]));
		}
		
		list.add(2, "France");
		assertEquals(7, list.size());
		assertEquals("France", list.get(2));
		assertEquals("Angola", list.get(3));
		assertEquals("India", list.get(1));
		
		assertEquals("France", list.remove(2));
		assertEquals("Zimbabwe", list.remove(5));
		
		assertEquals(-1, list.indexOf("France"));
		
		try {
			list.get(6);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNotNull(e);
		}
		
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNotNull(e);
		}
		
		try {
			assertFalse(list.add(null));
			list.add(1000, null);
			fail();
		} catch (NullPointerException e) {
			assertNotNull(e);
		}
		
		try {
			list.add(-1, "hello");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNotNull(e);
		}
		
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNotNull(e);
		}
		
		for (int i = list.size() - 1; i >= 0; i--) {
			assertEquals(names[i], list.remove(i));
			assertEquals(i, list.size());
		}
		
	}
	
	/**
	 * Tests an empty list.
	 */
	@Test
	public void testEmpty() {
		ArrayList list = new ArrayList();
		assertNotNull(list);
		assertTrue(list.isEmpty());
		
		assertFalse(list.contains("hello"));
		
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNotNull(e);
		}
		
		assertEquals(-1, list.indexOf("hello"));
		
		assertFalse(list.contains("hello"));
		
	}
	
	/**
	 * Tests the add method.
	 */
	@Test
	public void testAdd() {
		String[] names = { "USA", "India", "Angola", "Spain", "Canada" };
		
		ArrayList list = new ArrayList(2);
		assertNotNull(list);
		assertTrue(list.isEmpty());
		
		for (int i = 0; i < names.length; i++) {
			list.add(i, names[i]);
			assertEquals(names[i], list.get(i));
		}
	}

}
