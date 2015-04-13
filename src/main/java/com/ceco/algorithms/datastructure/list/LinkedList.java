package com.ceco.algorithms.datastructure.list;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * <p/>
 * Date added: 2015-02-13
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
        Node l = last;
        Node newNode = new Node(item, null, l);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(T item) {
        for (Node n = first; n != null; n = n.next) {
            if (item.equals(n.item)) {
                Node prev = n.previous;
                Node next = n.next;

                if (prev == null) {
                    first = next;
                } else {
                    prev.next = next;
                    n.previous = null;
                }

                if (next == null) {
                    last = prev;
                } else {
                    next.previous = prev;
                    n.next = null;
                }

                n.item = null;
                size--;

                return true;
            }
        }
        return false;
    }

    @Override
    public void addFirst(T item) {
        Node f = first;
        Node newNode = new Node(item, f, null);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.previous = newNode;
        }
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
