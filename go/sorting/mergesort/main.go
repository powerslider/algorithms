package main

import "fmt"

func MergeSort(input []int) []int {
	if len(input) <= 1 {
		return input
	}

	mid := len(input) / 2
	left := MergeSort(input[:mid])
	right := MergeSort(input[mid:])

	return Merge(left, right)
}

func ParallelMergeSort(input []int) []int {
	if len(input) <= 1 {
		return input
	}

	done := make(chan bool)
	mid := len(input) / 2

	var left []int
	go func() {
		left = ParallelMergeSort(input[:mid])
		done <- true
	}()

	right := ParallelMergeSort(input[mid:])
	<-done

	return Merge(left, right)
}

func Merge(left []int, right []int) []int {
	merged := make([]int, 0, len(left)+len(right))
	fmt.Println("left", left)
	fmt.Println("right", right)
	for len(left) > 0 || len(right) > 0 {
		if len(left) == 0 {
			fmt.Println("len(left) == 0", append(merged, right...))
			return append(merged, right...)
		} else if len(right) == 0 {
			fmt.Println("len(right) == 0", append(merged, left...))
			return append(merged, left...)
		} else if left[0] < right[0] {
			fmt.Println("left[0] < right[0]", append(merged, left[0]))
			merged = append(merged, left[0])
			left = left[1:]
		} else {
			fmt.Println("left[0] > right[0]", append(merged, right[0]))
			merged = append(merged, right[0])
			right = right[1:]
		}
	}

	return merged
}

func main() {
	data := []int{9, 4, 3, 6, 1, 2, 10, 5, 7, 8}
	fmt.Printf("%v\n%v\n", data, MergeSort(data))
	//fmt.Printf("%v\n%v\n", data, ParallelMergeSort(data))
}
