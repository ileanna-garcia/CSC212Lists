package edu.smith.cs.csc212.adtr.real;

import edu.smith.cs.csc212.adtr.ListADT;
import edu.smith.cs.csc212.adtr.errors.BadIndexError;


public class DoublyLinkedList<T> extends ListADT<T> {
	private Node<T> start;
	private Node<T> end;
	
	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}
	

	@Override
	public T removeFront() {
		checkNotEmpty();
		T value = start.value;
		this.start = start.after;
		return value;
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		if (start.after == null) {
			return removeFront();
		}
		T removed = end.value;
		end = end.before;
		end.after = null;
		return removed;
	}

	@Override 
	public T removeIndex(int index) {
		checkNotEmpty();
		T returnValue = null;
		if (index == 0) {
			return removeFront();
		}
		else if(index == size()-1) {
			return removeBack();
		}
		else if(index <0 || index >= size()){
			throw new BadIndexError(index);
		}
		else {
			int count =0;
			for(Node <T> n = start; n != null; n = n.after) {
				if (count == index) {
					
					Node <T> left = n.before;
					Node <T> right = n.after;
					 left.after = right;
					 right.before = left;
					 returnValue = n.value;
				}
				count ++;
			}
				
		}
		
			return returnValue;
	}

	@Override
	public void addFront(T item) {
		if (start == null) {
			this.start = new Node<T>(item);
		}
		
		else {
			Node<T> second = start;
			start = new Node<T>(item);
			start.after = second;
			second.before = start;
		}
	}

	@Override
	public void addBack(T item) {
		if (end == null) {
			this.start = this.end = new Node<T>(item);
					
		} 
		
		else {
			Node<T> secondLast = end;
			Node<T>newEnd = new Node<T>(item);
			newEnd.before = secondLast;
			secondLast.after = newEnd;
			end=newEnd;
		}
	}

	@Override
	public void addIndex(int index, T item) {
		checkNotEmpty();
		if(index == 0) {
			addFront(item);
		}
		else if (index == size()) { 	
			addBack(item);
		}
		else if(index < 0 || index > size()) {
			throw new BadIndexError(index);
		}
		else {					
			int count = 0;
			for (Node <T> n = start; n != null; n = n.after) {
				if(count == index) {
					Node <T> store = n;
					Node <T> left = n.before;
					Node <T> hold = new Node <T>(item);
					n = hold;
					n.value = item;
					n.before = left;
					left.after = n;
					n.after = store;
					store.before = n;
				}
				count ++;
			}
			
		}
	}

	@Override
	public T getFront() {
		checkNotEmpty();
		return this.start.value;
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		return this.end.value;
	}
	
	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		int at = 0;
		T value = null;
		if (index >= this.size() || index < 0){
			throw new BadIndexError(index);
		}
		for (Node<T> n = this.start; n != null; n = n.after) {
			if (at == index) {
				value= n.value;
			}
				at++;
		}
		return value;
	
	}
	
	public void setIndex(int index, T value) {
		checkNotEmpty();
		if(index == 0) {
			start.value = value;
		}
		else {
			int count =0;
			for (Node <T> n = start; n != null; n = n.after) {
				if(count == index ) {
					n.value= value;
				}
			count ++;
			}
			if(index >=count || index <0) {
				throw new BadIndexError(index);
			}
		}
	}

	@Override
	public int size() {
		 int count = 0; 
		 for (Node <T> n = start; n != null; n = n.after) {
		       count++; 
		   } 
		   return count; 
	}

	@Override
	public boolean isEmpty() {
		return start == null;
	}
	

	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of DoublyLinkedList to make a node.
	 * @param <T> the type of the values stored.
	 */
	public static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;
		/**
		 * Create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
}
