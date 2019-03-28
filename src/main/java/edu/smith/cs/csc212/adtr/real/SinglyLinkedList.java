package edu.smith.cs.csc212.adtr.real;

import edu.smith.cs.csc212.adtr.ListADT;
import edu.smith.cs.csc212.adtr.errors.BadIndexError;

public class SinglyLinkedList<T> extends ListADT<T> {
	/**
	 * The start of this list.
	 * Node is defined at the bottom of this file.
	 */
	Node<T> start;
	
	@Override
	public T removeFront() {
		checkNotEmpty();
		T value = start.value;
		start = start.next;
		return value;
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		Node <T> last = null;
		if (start.next == null) {
			return removeFront();
		}
		else {
			for (Node <T> n = start; n.next != null; n = n.next) {
				last = n;
			}
			T value = last.next.value;
			last.next = null;
			return value;
		}
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		T value = null;
		if (index == 0) {
			return removeFront();
		}
		else if (index >= this.size() || index < 0){
			throw new BadIndexError(index);
		}
		else {
			int count =0;
			for(Node <T> n = start; n != null; n = n.next) {
				if (count == index - 1) {
					 value = n.next.value;	
					 n.next = n.next.next;
					
				}
				count ++;
				}
				
		}
		return value;
		}
	@Override
	public void addFront(T item) {
		this.start = new Node<T>(item, start);
	}

	@Override
	public void addBack(T item) {
		if(start == null) {
			addFront(item);
		}
		else {
			Node <T> temp = start;
			for (Node <T> n = start; n != null; n = n.next) {
				temp = n;
			}
			temp.next = new Node <T>(item,null);
		}
	}

	@Override
	public void addIndex(int index, T item) {
		if(index == 0) {
			addFront(item);
		}
		else {
			int count =0;
			for (Node <T> n = start; n != null; n = n.next) {
				if(count == index -1) {
					Node<T> temp = n;
					n.next = new Node <T>(item,temp.next);
					
				}
			count ++;
			}
			if(index > count || index <0) {
				throw new BadIndexError(index);
			}
		}
	}
	
	
	
	@Override
	public T getFront() {
		checkNotEmpty();
		return start.value;
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		Node <T> last = null;
		for (Node <T> n = start; n !=null; n = n.next) {
			last =n;
		}
		return last.value;
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			if (at++ == index) {
				return n.value;
			}
		}
		throw new BadIndexError(index);
	}
	

	@Override
	public void setIndex(int index, T value) {
		checkNotEmpty();
		if(index == 0) {
			start.value = value;
		}
		else {
			int count =0;
			for (Node <T> n = start; n != null; n = n.next) {
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
		for (Node<T> n = this.start; n != null; n = n.next) {
			
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return this.start == null;
	}
	
	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of SinglyLinkedList to make a node.
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes after me?
		 */
		public Node<T> next;
		/**
		 * What value is stored in this node?
		 */
		public T value;
		/**
		 * Create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
			
		}
	}

}
