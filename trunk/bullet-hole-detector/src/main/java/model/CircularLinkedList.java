package model;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Custom list implementation
 * 
 * @author Erik Stens
 */
public class CircularLinkedList<E> extends AbstractList<E> {
	private static class Node<E> {
		private E		data;
		private Node<E>	next;
		
		/**
		 * Constructor for a node without a successor
		 * 
		 * @param data
		 */
		private Node(E data) {
			this(data, null);
		}
		
		/**
		 * Constructor for a node with a successor
		 * 
		 * @param data
		 *            the data
		 * @param next
		 *            the next node
		 */
		private Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}
	
	/**
	 * A custom iterator which supports addition of new elements to the list
	 */
	private class CircularLinkedListIterator implements Iterator<E> {
		private Node<E>	beforeLastItemReturned, lastItemReturned, nextItem;
		
		private int		indexLastReturned;
		private boolean	itemReturned;
		
		private CircularLinkedListIterator() {
			if (isEmpty()) {
				nextItem = null;
			} else {
				nextItem = head;
			}
			lastItemReturned = beforeLastItemReturned = null;
			
			indexLastReturned = -1;
			itemReturned = false;
		}
		
		/**
		 * @return true if there is a next element in the list
		 */
		@Override
		public boolean hasNext() {
			return nextItem != null;
		}
		
		/**
		 * Returns the next element in the list
		 * 
		 * @return the data in the node
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			beforeLastItemReturned = lastItemReturned;
			lastItemReturned = nextItem;
			nextItem = nextItem.next;
			indexLastReturned = lastItemReturned != head ? indexLastReturned + 1 : 0;
			itemReturned = true;
			return lastItemReturned.data;
		}
		
		/**
		 * Removes the last returned element from the list. May only be used after a call to next()
		 */
		@Override
		public void remove() {
			if (!itemReturned) {
				throw new IllegalStateException();
			}
			
			if (head == tail) { // list with one element
				head = tail = null;
			} else if (lastItemReturned == head) { // list with more than one element where the element to remove is equal to the head of the list
				head = nextItem;
				tail.next = head;
			} else if (lastItemReturned == tail) { // list with more than one element where the element to remove is equal to the tail of the list
				tail = beforeLastItemReturned;
				tail.next = head;
			} else { // list with more than one element where the element to remove is neither the head nor the tail
				beforeLastItemReturned.next = nextItem;
			}
			
			size--;
			indexLastReturned--;
			lastItemReturned = beforeLastItemReturned;
			itemReturned = false;
		}
		
		public void add(E data) {
			if (isEmpty()) { // empty list
				beforeLastItemReturned = lastItemReturned = nextItem = tail = head = new Node<E>(data);
				tail.next = head;
			} else {
				beforeLastItemReturned = lastItemReturned;
				lastItemReturned = new Node<E>(data, nextItem);
				beforeLastItemReturned.next = lastItemReturned;
			}
			size++;
			indexLastReturned++;
			
			itemReturned = false;
		}
	}
	
	private Node<E>	head	= null, tail = null;
	private int		size	= 0;
	
	public CircularLinkedList() {}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Appends the specified element to the end of this list
	 * 
	 * @param e
	 *            the element to be appended to this list
	 * @return true (as specified by Collection.add(T))
	 */
	@Override
	public boolean add(E e) {
		if (isEmpty()) {
			tail = head = new Node<E>(e);
		} else {
			tail = tail.next = new Node<E>(e);
		}
		size++;
		return true;
	}
	
	/**
	 * Inserts the specified element at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices)
	 * 
	 * @param index
	 *            the index at which the specified element is to be inserted
	 * @param e
	 *            the element to be inserted
	 * 
	 * @return the element at the specified position in this list
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size())
	 */
	@Override
	public void add(int index, E e) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index == size) {
			add(e);
		}
		
		CircularLinkedListIterator iterator = circularLinkedListIterator();
		for (int i = 0; i < index && iterator.hasNext(); i++, iterator.next()) {}
		
		iterator.add(e);
	}
	
	/**
	 * Returns the element at the specified position in this list
	 * 
	 * @param index
	 *            the index of the element to return
	 * 
	 * @return the element at the specified position in this list
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size())
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index == size - 1) {
			return tail.data;
		}
		
		CircularLinkedListIterator iterator = circularLinkedListIterator();
		for (int i = 0; i < index && iterator.hasNext(); i++, iterator.next()) {}
		
		return iterator.next();
	}
	
	public E getFirst() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("Can't return an element from an empty list!");
		}
		
		return head.data;
	}
	
	public E getLast() throws RuntimeException {
		if (isEmpty()) {
			throw new RuntimeException("Can't return an element from an empty list!");
		}
		
		return tail.data;
	}
	
	/**
	 * @param index
	 *            the index of the element to be removed
	 * 
	 * @return the element previously at the specified position
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (index < 0 || index >= size())
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		CircularLinkedListIterator iterator = circularLinkedListIterator();
		for (int i = 0; i < index && iterator.hasNext(); i++, iterator.next()) {}
		E lastElement = iterator.next();
		iterator.remove();
		return lastElement;
	}
	
	/**
	 * Creates a string representation of the contents of the list
	 * 
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		
		Iterator<E> iterator = iterator();
		while (iterator.hasNext()) {
			output.append(iterator.next().toString());
			if (iterator.hasNext()) {
				output.append(", ");
			}
		}
		return output.toString();
	}
	
	/**
	 * Returns a new iterator
	 * 
	 * @return the iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new CircularLinkedListIterator();
	}
	
	/**
	 * Returns a new CircularLinkedListIterator
	 * 
	 * @return the CircularLinkedListIterator
	 */
	public CircularLinkedListIterator circularLinkedListIterator() {
		return new CircularLinkedListIterator();
	}
}