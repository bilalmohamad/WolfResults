package edu.ncsu.csc216.wolf_results.util;

import java.util.Collection;

/**
 * This class creates an ArrayList through the implementation of the List
 * interface.
 * 
 * @author Bilal Mohamad
 * @author Marwah Mahate
 */
public class ArrayList implements List {

	/** The serialVersionUID for serialization */
	@SuppressWarnings("unused")
	// TODO Make sure the serialization is done correctly
	private static final long serialVersionUID = 1L;

	/** The constant value used for resizing the list size */
	private static final int RESIZE = 10;

	/** An array of type E */
	private Object[] list;

	/** the size of the list */
	private int size;

	/**
	 * Creates an ArrayList with the default size
	 */
	public ArrayList() {
		this(RESIZE);
	}

	/**
	 * Creates an ArrayList with the entered size
	 * 
	 * @param capacity Create ArrayList with a specified capacity
	 */
	public ArrayList(int capacity) {
		
		if (capacity <= 0) {
			throw new IllegalArgumentException();
		}
		list = new Object[capacity];
		size = 0;
	}

	/**
	 * Returns the number of elements in this list. If this list contains more than
	 * Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 *
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this list contains no elements.
	 *
	 * @return true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element e such
	 * that (o==null ? e==null : o.equals(e)).
	 *
	 * @param o element whose presence in this list is to be tested
	 * @return true if this list contains the specified element
	 */
	@Override
	public boolean contains(Object o) {
		if (size == 0 || o == null) {
			return false;
		} else {
			for (int i = 0; i < size; i++) {
				if (list[i].equals(o)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Appends the specified element to the end of this list (optional operation).
	 *
	 * Lists that support this operation may place limitations on what elements may
	 * be added to this list. In particular, some lists will refuse to add null
	 * elements, and others will impose restrictions on the type of elements that
	 * may be added. List classes should clearly specify in their documentation any
	 * restrictions on what elements may be added.
	 *
	 * @param o element to be appended to this list
	 * @return true (as specified by {@link Collection#add})
	 */
	@Override
	public boolean add(Object o) {
		
		add(size, o);
		return true;
		
		/*if (o == null) {
			return false;
		} else {
			if (size == list.length) {
				// resize the list
				resize();
			}
			list[size] = o;
			size++;
			return true;
		}*/
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@Override
	public Object get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		} else {
			return list[index];
		}
	}

	/**
	 * Inserts the specified element at the specified position in this list
	 * (optional operation). Shifts the element currently at that position (if any)
	 * and any subsequent elements to the right (adds one to their indices).
	 *
	 * @param index   index at which the specified element is to be inserted
	 * @param element element to be inserted
	 * @throws NullPointerException      if the specified element is null and this
	 *                                   list does not permit null elements
	 * @throws IllegalArgumentException  if some property of the specified element
	 *                                   prevents it from being added to this list
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@Override
	public void add(int index, Object element) {
		if (element == null) {
			throw new NullPointerException();
		} else if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		} else {
			
			for (int i = 0; i < size; i++) {
				if (list[i].equals(element)) {
					throw new IllegalArgumentException();
				}
			}
			
			if (size == list.length) {
				resize();
			}
			for (int i = size - 1; i >= index; i--) {
				list[i + 1] = list[i];
			}
			list[index] = element;
			size++;
		}

	}

	/**
	 * Removes the element at the specified position in this list (optional
	 * operation). Shifts any subsequent elements to the left (subtracts one from
	 * their indices). Returns the element that was removed from the list.
	 *
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@Override
	public Object remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		} else {
			Object value = list[index];
			for (int i = index; i < size - 1; i++) {
				list[i] = list[i + 1];
			}
			size--;
			return value;
		}
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally, returns
	 * the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or
	 * -1 if there is no such index.
	 *
	 * @param o element to search for
	 * @return the index of the first occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(Object o) {
		if (size == 0 || o == null) {
			return -1;
		} else {
			for (int i = 0; i < size; i++) {
				if (list[i].equals(o)) {
					return i;
				}
			}
			return -1;
		}
	}

	private void resize() {
		// resize the list
		Object[] temp = new Object[2 * list.length];
		for (int i = 0; i < list.length; i++) {
			temp[i] = list[i];
		}
		list = temp;
	}
}
