package com.ceco.algorithms.datastructure.queue;

public interface Queue<T> {
	
	boolean isEmpty();
	
	void enqueue(T item);
	
	T dequeue();
}
