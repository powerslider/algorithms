package com.ceco.algorithms.datastructure.stack;


import java.util.EmptyStackException;


public class ArrayStack<T> implements Stack<T>{

    private static final int DEFAULT_CAPACITY = 5;

	private T[] arr;
    private int capacity;
    private int top;
	
	public ArrayStack() {
		this.arr = (T[]) new Object[DEFAULT_CAPACITY];
        this.capacity = arr.length;
        this.top = -1;
	}
	
	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public void push(T item) {
		if (top + 1  == capacity) {
			resize(capacity * 2);
		}
		arr[++top] = item;
	}

	@Override
	public T pop() {
        if (isEmpty()) {
           throw new EmptyStackException();
        }
		
		if (top > 0 && top + 1 == capacity / 4) {
			resize(capacity / 2);
		}
		
		return arr[top--];
	}

    @Override
    public T peek() {
       if (isEmpty()) {
           throw new EmptyStackException();
       }

       return arr[top];
    }

    private void resize(int capacity) {
		T[] newArr = (T[]) new Object[capacity];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
		this.arr = newArr;
        this.capacity = arr.length;
	}
}
