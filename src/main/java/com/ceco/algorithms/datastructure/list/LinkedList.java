package com.ceco.algorithms.datastructure.list;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 13 Feb 2015
 */
public class LinkedList<T> implements List<T> {

    private int size = 0;

    private class Node {

        T item;
        Node next;
        Node previous;

        Node(T item, Node next, Node previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }
    }

    private Node first;

    private Node last;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean add(T item) {
        // get reference to the lastly added node
        Node l = last;

        // create the new node
        Node newNode = new Node(item, null, l);

        // assign it as the new lastly added node
        last = newNode;

        // if there is no lastly added node -> that is our first added node
        // otherwise we assign the new node to the next reference of the
        // lastly added one
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }

        // increment size of list
        size++;

        return true;
    }

    @Override
    public boolean remove(T item) {
        // iterate list by following next references
        for (Node n = first; n != null; n = n.next) {
            if (item.equals(n.item)) {
                // get prev ref of node marked for removal
                Node prev = n.previous;

                // get next ref of node marked for removal
                Node next = n.next;

                // if there is no prev ref than that is the first node of the list
                // otherwise we bypass the node marked for removal by assigning
                // the next ref of prev to the next ref of the node marked for removal
                // and delete its reference to the previous element
                if (prev == null) {
                    first = next;
                } else {
                    prev.next = next;
                    n.previous = null;
                }

                // if there is no next ref than that is the last element of the list
                // otherwise we bypass the node marked for removal by assigning
                // the prev ref of next to the prev ref of the node marked for removal
                // and delete its reference to the next element
                if (next == null) {
                    last = prev;
                } else {
                    next.previous = prev;
                    n.next = null;
                }

                // delete node content
                n.item = null;

                // decrement list size
                size--;

                return true;
            }
        }
        return false;
    }

    @Override
    public void addFirst(T item) {
        // get reference to the firstly added node
        Node f = first;

        // create the new node
        Node newNode = new Node(item, f, null);

        // assign it as the new firstly added node
        first = newNode;

        // if there was no node added before this one
        // than now that is going to be our lastly added node
        // otherwise we simply position it as the previous ref
        // of the first node so that it can become the new first node
        if (f == null) {
            last = newNode;
        } else {
            f.previous = newNode;
        }

        // increment the size of the list
        size++;
    }

    @Override
    public T getFirst() {
        T f = first.item;
        if (f == null)
            throw new NoSuchElementException();
        return f;
    }

    @Override
    public T getLast() {
        T l = last.item;
        if (l == null)
            throw new NoSuchElementException();
        return l;
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

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
//        System.out.println(list.getFirst());
//        System.out.println(list.getLast());
//        list.remove(1);
//        System.out.println(list.getFirst());
//        list.remove(3);
//        System.out.println(list.getLast());
        //System.out.println(list.size());

        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
