package main

import (
	"fmt"
	"strconv"
	"strings"
)

type MinHeap struct {
	items []int
}

func NewMinHeap() *MinHeap {
	return &MinHeap{
		items: make([]int, 0),
	}
}

func getParentIndex(index int) int {
	return (index - 1) / 2
}

func getLeftChildIndex(index int) int {
	return 2*index + 1
}

func getRightChildIndex(index int) int {
	return 2*index + 2
}

func (h *MinHeap) Push(item int) {
	h.items = append(h.items, item)

	// restore the heap property starting from the last element
	h.heapifyUp(len(h.items) - 1)
}

func (h *MinHeap) Peek() (int, error) {
	if len(h.items) == 0 {
		return 0, fmt.Errorf("heap is empty")
	}

	return h.items[0], nil
}

func (h *MinHeap) Pop() (int, error) {
	if len(h.items) == 0 {
		return 0, fmt.Errorf("heap is empty")
	}

	minItem := h.items[0]

	lastIndex := len(h.items) - 1
	// move the last element to the root
	h.items[0] = h.items[lastIndex]
	// remove the last element
	h.items = h.items[:lastIndex]

	// restore the heap property starting from the root
	h.heapifyDown(0)

	return minItem, nil
}

func (h *MinHeap) FindKthLargest(k int) int {
	for len(h.items) > k {
		_, err := h.Pop()
		if err != nil {
			panic(err)
		}
	}

	kThItem, err := h.Pop()
	if err != nil {
		panic(err)
	}

	return kThItem
}

func (h *MinHeap) String() string {
	var sb strings.Builder
	for _, item := range h.items {
		sb.WriteString(strconv.Itoa(item) + "\n")
	}

	return sb.String()
}

func (h *MinHeap) heapifyUp(index int) {
	for index > 0 {
		parentIndex := getParentIndex(index)
		if h.items[index] < h.items[parentIndex] {
			h.items[index], h.items[parentIndex] = h.items[parentIndex], h.items[index]
			index = parentIndex
		} else {
			break
		}
	}
}

func (h *MinHeap) heapifyDown(index int) {
	lastIndex := len(h.items) - 1

	for {
		smallestIndex := index
		leftChildIndex := getLeftChildIndex(index)
		rightChildIndex := getRightChildIndex(index)

		// if the left child is smaller than the current index, make it the smallest
		if leftChildIndex <= lastIndex && h.items[leftChildIndex] < h.items[smallestIndex] {
			smallestIndex = leftChildIndex
		}

		// if the right child is smaller than the current index, make it the smallest
		if rightChildIndex <= lastIndex && h.items[rightChildIndex] < h.items[smallestIndex] {
			smallestIndex = rightChildIndex
		}

		// if the smallestIndex is not the current index, then swap them
		if smallestIndex != index {
			h.items[smallestIndex], h.items[index] = h.items[index], h.items[smallestIndex]
			index = smallestIndex
		} else {
			break
		}
	}
}

func main() {
	heap := NewMinHeap()
	// Push elements into the heap
	heap.Push(10)
	heap.Push(5)
	heap.Push(3)
	heap.Push(2)
	heap.Push(8)

	peekedMinItemBefore, err := heap.Peek()
	if err != nil {
		panic(err)
	}

	fmt.Printf("peeked min item is %d\n", peekedMinItemBefore)
	fmt.Println(heap)

	extractedMinItem, err := heap.Pop()
	if err != nil {
		panic(err)
	}

	fmt.Printf("extracted min item is %d\n", extractedMinItem)
	fmt.Println(heap)

	peekedMinItemAfter, err := heap.Peek()
	if err != nil {
		panic(err)
	}

	fmt.Printf("peeked min item is %d\n", peekedMinItemAfter)
	fmt.Println(heap)

	fmt.Printf("the second largest element is: %d\n", heap.FindKthLargest(2))
}
