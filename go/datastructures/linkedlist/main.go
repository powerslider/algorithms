package main

import (
	"fmt"
	"strconv"
	"strings"
)

type Node struct {
	data int
	next *Node
}

func NewNode(data int) *Node {
	return &Node{
		data: data,
	}
}

type LinkedList struct {
	head *Node
}

func NewLinkedList() *LinkedList {
	return &LinkedList{}
}

func (l *LinkedList) PushFront(data int) {
	newNode := NewNode(data)
	if l.head == nil {
		l.head = newNode
		return
	}

	newNode.next = l.head
	l.head = newNode
}

func (l *LinkedList) PushBack(data int) {
	newNode := NewNode(data)
	if l.head == nil {
		l.head = newNode
		return
	}

	current := l.head
	for current.next != nil {
		current = current.next
	}

	current.next = newNode
}

func (l *LinkedList) Remove(data int) {
	if l.head == nil {
		fmt.Println("list is empty")
		return
	}

	if l.head.data == data {
		l.head = l.head.next
		return
	}

	current := l.head
	for current.next != nil {
		if current.next.data == data {
			current.next = current.next.next
		}
		current = current.next
	}
}

func (l *LinkedList) Reverse() {
	var (
		next *Node
		prev *Node
	)
	current := l.head

	for current != nil {
		// assign the next node after current
		next = current.next
		// assign the prev node before current to the next node after current
		current.next = prev

		// advance with one step by making prev the current
		prev = current
		// advance with one step by making the current pointing to the next
		current = next
	}

	l.head = prev
}

func (l *LinkedList) HasCycle() bool {
	if l.head == nil {
		return false
	}

	oneStep, twoStep := l.head, l.head.next
	for twoStep != nil && twoStep.next != nil {
		if oneStep == twoStep {
			return true
		}
		oneStep = oneStep.next
		twoStep = twoStep.next.next
	}

	return false
}

func (l *LinkedList) String() string {
	var sb strings.Builder
	current := l.head
	for current != nil {
		sb.WriteString(strconv.Itoa(current.data) + "\n")
		current = current.next
	}

	return sb.String()
}

func main() {
	list := NewLinkedList()

	list.PushFront(20)
	list.PushFront(10)
	list.PushBack(30)
	list.PushBack(40)

	list.Remove(30)

	fmt.Println(list)
	fmt.Println(list.HasCycle())

	list.Reverse()
	fmt.Println(list)
}
