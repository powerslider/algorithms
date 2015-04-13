package com.ceco.algorithms.datastructure.stack;


import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<T> implements Stack<T>, Iterable<T> {
	
	private class Node {
		T item;
		Node next;
	}
	
	private Node first = null;
	
	@Override
	public boolean isEmpty(){
		return first == null;
	}
	
	@Override
	public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

		T item = first.item;
		first = first.next;
		
		return item;
	}

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return first.item;
    }

    @Override
	public void push(T item){
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<T> {
		
		private Node current = first;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T item = null;
			try {
				item = current.item;
				current = current.next;
			} catch (NoSuchElementException nsee) {
				nsee.printStackTrace();
			}
			
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Cannot remove when iterating!");
		}
	}
}
