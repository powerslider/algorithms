package main

import (
	"fmt"
)

func binarySearch(key int, input []int) int {
	low := 0
	high := len(input) - 1
	mid := low + (high-low)/2

	for low <= high {
		mid = low + ((high - low) / 2)
		if input[mid] == key {
			return mid
		}

		if input[mid] < key {
			low = mid + 1
		} else {
			high = mid - 1
		}
		fmt.Println("LOW: ", low)
		fmt.Println("HIGH: ", high)
	}

	return mid
}

func main() {
	input := []int{1, 2, 3, 4, 5, 6, 7, 8}
	fmt.Println(binarySearch(8, input))
}
