package edu.ncsu.csc216.wolf_results.util;

import java.util.Collection;

/**
 * This class will create a sorted linked list through the use of abstract
 * Comparable class, SortedList interface, and with a data structure of linked
 * Nodes
 * 
 * @author Bilal Mohamad
 * @author Marwah Mahate
 *
 * @param <E> the object type of the SortedLinkedList
 */
public class SortedLinkedList<E extends Comparable<E>> implements SortedList<E> {

	/** The element at the front of the SortedLinkedList */
	private Node head;

	private int size;

	/**
	 * Constructor used for creating SortedLinkedList objects
	 */
	public SortedLinkedList() {
		super();
		this.head = null;
		this.size = 0;
	}

	/**
	 * Returns the number of elements in this list. If this list contains more than
	 * Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 *
	 * @return the number of elements in this list
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element a such
	 * that (o==null ? a==null : o.equals(a)).
	 *
	 * @param e element whose presence in this list is to be tested
	 * @return true if this list contains the specified element
	 */
	public boolean contains(E e) {
		if (e == null) {
			return false;
		}
		Node current = head;
		while (current != null) {
			if (current.value.equals(e)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * Adds the specified element to list in sorted order
	 *
	 * @param e element to be appended to this list
	 * @return true (as specified by {@link Collection#add})
	 * @throws NullPointerException     if e is null
	 * @throws IllegalArgumentException if list already contains e
	 */
	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (this.contains(e)) {
			throw new IllegalArgumentException();
		}

		// special case when list is empty
		if (this.head == null) {
			this.head = new Node(e, null);
			this.size = 1;
			return true;
		}

		Node current = head;
		Node previous = null;
		while (current != null) {
			if (e.compareTo(current.value) < 0) {
				if (previous == null) {
					Node node = new Node(e, current);
					this.head = node;
					this.size++;
					return true;
				} else {
					Node node = new Node(e, current);
					previous.next = node;
					size++;
					return true;
				}
			}
			previous = current;
			current = current.next;
		}

		// insert node at the end of the list
		Node node = new Node(e, null);
		previous.next = node;
		size++;
		return true;
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node current = head;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				return current.value;
			}
			current = current.next;
		}
		throw new IndexOutOfBoundsException();
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices). Returns
	 * the element that was removed from the list.
	 *
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		// special case of list index == 0 && size == 1
		if (index == 0 && size == 1) {
			E value = this.head.value;
			this.head = null;
			this.size = 0;
			return value;
		}

		Node current = head;
		Node previous = null;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				if (previous != null) {
					previous.next = current.next;
					this.size--;
					return current.value;
				} else {
					this.head = current.next;
					this.size--;
					return current.value;
				}
			}
			previous = current;
			current = current.next;
		}
		throw new IndexOutOfBoundsException();
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally, returns
	 * the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or
	 * -1 if there is no such index.
	 *
	 * @param e element to search for
	 * @return the index of the first occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(E e) {
		if (e == null) {
			return -1;
		}
		Node current = head;
		for (int i = 0; i < size; i++) {
			if (current.value.equals(e)) {
				return i;
			}
			current = current.next;
		}
		return -1;
	}

	/**
	 * Hashes the entered data using the hash algorithm
	 * 
	 * @return the hashed information
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		Node current = head;
		while (current != null) {
			result = prime * result + current.hashCode();
			current = current.next;
		}
		return result;
	}

	/**
	 * Compares the current object with the entered object. If they are equals then
	 * it will return true otherwise false
	 * 
	 * @param obj the object used for comparison
	 * 
	 * @return true if the current object and the entered object are the same false
	 *         if the current object and the entered object are not the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj instanceof SortedLinkedList<?>) {
			@SuppressWarnings("unchecked")
			SortedLinkedList<E> other = (SortedLinkedList<E>) obj;
			if (this.size() != other.size()) {
				return false;
			}
			Node current1 = this.head;
			Node current2 = other.head;
			while (current1 != null) {
				if (!current1.equals(current2)) {
					return false;
				}
				current1 = current1.next;
				current2 = current2.next;
			}
			return true;
		}
		return false;
	}

	/**
	 * Converts the SortedLinkedList into a String
	 * 
	 * @return the SortedLinkedList converted into a String format
	 */
	@Override
	public String toString() {
		
		if (size == 0) {
			return "[]";
		}
		
		String result = "[";
		
		if (head.value != null) {
			
			result += head.value;
			Node current = head;
			
			for (int i = 0; i < size - 1; i++) {
				current = current.next;
				result += ", " + current.value;
			}
		}
		result += "]";
		
		return result;
		
		/*if (head == null) {
			return "[]";
		}
		String result = "[";
		Node current = head;
		for (int i = 0; i < size; i++) {
			result += current.value;
			current = current.next;
		}
		result += "]";
		return result;*/
	}

	/**
	 * Creates the node system for the LinkedList
	 * 
	 * @author Bilal Mohamad
	 * @author Marwah Mahate
	 * 
	 */
	private class Node {

		/** The value inside the node */
		private E value;

		/** The next node in the list */
		private Node next;

		/**
		 * Constructs a new Node with the given data, then sets the next Node that is
		 * referenced
		 * 
		 * @param data the data to be set
		 * @param next the next Node that the current Node will point to
		 */
		public Node(E value, Node next) {
			this.value = value;
			this.next = next;
		}

		/**
		 * Hashes the entered data using the hash algorithm
		 * 
		 * @return the hashed information
		 */
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + this.value.hashCode();
			return result;
		}

		/**
		 * Compares the current object with the entered object. If they are equals then
		 * it will return true otherwise false
		 * 
		 * @param obj the object used for comparison
		 * 
		 * @return true if the current object and the entered object are the same false
		 *         if the current object and the entered object are not the same
		 */
		public boolean equals(Object obj) {
			@SuppressWarnings("unchecked")
			Node other = (Node) obj;
			return this.value.equals(other.value);
		}
	}

	/**
	 * Returns true if this list contains no elements.
	 *
	 * @return true if this list contains no elements
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}
}