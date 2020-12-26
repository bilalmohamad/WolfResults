package edu.ncsu.csc216.wolf_results.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests SortedLinkedList.
 * 
 * @author Marwah Mahate
 * @author Bilal Mohamad
 *
 */
public class SortedLinkedListTest {

	/**
	 * Tests alphabetizing a list of words.
	 */
	@Test
	public void testSortedNames() {
		String[] names = { "USA", "India", "Angola", "Spain", "Canada" };
		String[] expected = { "Angola", "Canada", "India", "Spain", "USA" };

		SortedLinkedList<String> sortedNames = new SortedLinkedList<String>();
		assertNotNull(sortedNames);
		assertTrue(sortedNames.isEmpty());

		for (int i = 0; i < names.length; i++) {
			sortedNames.add(names[i]);
			assertEquals(i + 1, sortedNames.size());
		}

		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], sortedNames.get(i));
			assertEquals(i, sortedNames.indexOf(expected[i]));
		}
		
		sortedNames.add("Zimbabwe");
		assertEquals(6, sortedNames.size());
		assertEquals(5, sortedNames.indexOf("Zimbabwe"));
		assertEquals("Zimbabwe", sortedNames.get(5));

		try {
			sortedNames.add("USA");
			fail();
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}

		try {
			sortedNames.add(null);
			fail();
		} catch (NullPointerException e) {
			assertNotNull(e);
		}
	}

	/**
	 * Tests the contains() method.
	 */
	@Test
	public void testContains() {
		String[] names = { "USA", "India", "Angola", "Spain", "Canada" };

		SortedLinkedList<String> sortedNames = new SortedLinkedList<String>();

		assertFalse(sortedNames.contains("xyz"));

		for (int i = 0; i < names.length; i++) {
			sortedNames.add(names[i]);
			assertTrue(sortedNames.contains(names[i]));
		}
		assertFalse(sortedNames.contains("xyz"));
		assertEquals(-1, sortedNames.indexOf("xyz"));
	}

	/**
	 * Tests an empty sorted linked list.
	 */
	@Test
	public void testEmpty() {
		SortedLinkedList<String> sortedNames = new SortedLinkedList<String>();

		assertTrue(sortedNames.isEmpty());
		assertFalse(sortedNames.contains("USA"));
		assertEquals("[]", sortedNames.toString());
		assertTrue(sortedNames.equals(sortedNames));
		// assertFalse(sortedNames.equals(null));
		assertFalse(sortedNames.equals(""));

		try {
			sortedNames.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNotNull(e);
		}

		try {
			sortedNames.get(1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNotNull(e);
		}

		try {
			sortedNames.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNotNull(e);
		}

		assertFalse(sortedNames.contains("USA"));
		assertFalse(sortedNames.contains(null));
		
		assertEquals(-1, sortedNames.indexOf(null));
		assertEquals(-1, sortedNames.indexOf("USA"));
		
		try {
			sortedNames.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNotNull(e);
		}
	}

	/**
	 * Tests the remove method.
	 */
	@Test
	public void testRemove() {
		String[] names = { "USA", "India", "Angola", "Spain", "Canada" };
		String[] expected = { "Angola", "Canada", "India", "Spain", "USA" };

		SortedLinkedList<String> sortedNames = new SortedLinkedList<String>();
		assertNotNull(sortedNames);
		assertTrue(sortedNames.isEmpty());

		for (int i = 0; i < names.length; i++) {
			sortedNames.add(names[i]);
			assertEquals(i + 1, sortedNames.size());
		}

		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], sortedNames.remove(0));
			assertEquals(expected.length - i - 1, sortedNames.size());
		}

		for (int i = 0; i < names.length; i++) {
			sortedNames.add(names[i]);
			assertEquals(i + 1, sortedNames.size());
		}
		
		assertFalse(sortedNames.isEmpty());
		
		assertEquals("India", sortedNames.remove(2));
		assertEquals(4, sortedNames.size());
		
		assertEquals("USA", sortedNames.remove(3));
		assertEquals(3, sortedNames.size());
		
		assertEquals("Angola", sortedNames.remove(0));
		assertEquals(2, sortedNames.size());
		
		assertEquals("Spain", sortedNames.remove(1));
		assertEquals(1, sortedNames.size());
		
		assertEquals("Canada", sortedNames.remove(0));
		assertEquals(0, sortedNames.size());
		
		assertTrue(sortedNames.isEmpty());

	}
	
	/**
	 * Tests the equals() method.
	 */
	@Test
	public void testEquals() {
		String[] names = { "USA", "India", "Angola", "Spain", "Canada" };

		SortedLinkedList<String> sortedNames = new SortedLinkedList<String>();
		for (int i = 0; i < names.length; i++) {
			sortedNames.add(names[i]);
			assertEquals(i + 1, sortedNames.size());
		}
		
		SortedLinkedList<String> duplicates = new SortedLinkedList<String>();
		assertFalse(sortedNames.equals(duplicates));
		assertFalse(sortedNames == null);
		
		for (int i = 0; i < names.length; i++) {
			duplicates.add(names[i]);
			assertEquals(i + 1, duplicates.size());
		}
		
		assertTrue(sortedNames.equals(duplicates));
		
		assertEquals(duplicates.hashCode(), sortedNames.hashCode());
		
		assertEquals(duplicates.toString(), sortedNames.toString());
	}
}
