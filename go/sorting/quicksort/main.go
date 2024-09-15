package main

import "fmt"

func QuickSort(input []int) []int {
	if len(input) < 2 {
		return input
	}

	// partition the array and get the index of the pivot
	pivotIndex := partition(input)

	// recursively apply quicksort to the left and right sub-arrays
	QuickSort(input[:pivotIndex])
	QuickSort(input[pivotIndex+1:])

	return input
}

func partition(input []int) int {
	startPivotIndex := len(input) - 1
	// use the last element as the pivot
	pivot := input[startPivotIndex]
	i := 0

	for j := 0; j < len(input)-1; j++ {
		if input[j] <= pivot {
			// swap the smaller element to the left side
			input[i], input[j] = input[j], input[i]
			i++
		}
	}

	// place the pivot in its correct position
	input[i], input[startPivotIndex] = input[startPivotIndex], input[i]

	fmt.Println("PIVOT INDEX", i)

	// return the index of the pivot element
	return i
}

func main() {
	data := []int{9, 4, 3, 6, 1, 2, 10, 5, 7, 8}
	fmt.Printf("%v\n", QuickSort(data))
}
