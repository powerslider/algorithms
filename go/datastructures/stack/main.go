package main

import (
	"fmt"
	"strconv"
	"strings"
)

type Stack struct {
	items []int
}

func NewStack() *Stack {
	return &Stack{
		items: make([]int, 0),
	}
}

func (s *Stack) Push(item int) {
	s.items = append(s.items, item)
}

func (s *Stack) Pop() (int, error) {
	if len(s.items) == 0 {
		return 0, fmt.Errorf("stack is empty")
	}

	topIndex := len(s.items) - 1
	// select the top element
	topItem := s.items[topIndex]
	// remove the top element
	s.items = s.items[:topIndex]

	// return the popped element
	return topItem, nil
}

func (s *Stack) Peek() (int, error) {
	if len(s.items) == 0 {
		return 0, fmt.Errorf("stack is empty")
	}

	topIndex := len(s.items) - 1
	// select the top element
	topItem := s.items[topIndex]

	// return the popped element
	return topItem, nil
}

func (s *Stack) String() string {
	var sb strings.Builder

	for _, item := range s.items {
		sb.WriteString(strconv.Itoa(item) + "\n")
	}

	return sb.String()
}

func main() {
	stack := NewStack()

	stack.Push(1)
	stack.Push(2)
	stack.Push(3)
	stack.Push(4)
	stack.Push(5)
	stack.Push(6)

	fmt.Println(stack)

	poppedItem, err := stack.Pop()
	if err != nil {
		panic(err)
	}

	fmt.Printf("%d is popped\n", poppedItem)

	fmt.Println(stack)
}
