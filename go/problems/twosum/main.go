package main

import "fmt"

// Given an array of integers nums and an integer target,
// return indices of the two numbers such that they add up to target.
func twoSum(nums []int, target int) []int {
	numsMap := make(map[int]int)

	for i, num := range nums {
		// calculate the complement
		complement := target - num

		// check if the complement is stored in the map
		if idx, found := numsMap[complement]; found {
			return []int{idx, i}
		}

		// store the index of the current number in the slice
		numsMap[num] = i
	}

	return nil
}

func main() {
	nums := []int{2, 7, 11, 15}
	target := 9
	result := twoSum(nums, target)
	fmt.Println(result) // Output: [0, 1]

	// Additional test cases
	fmt.Println(twoSum([]int{3, 2, 4}, 6))             // Output: [1, 2]
	fmt.Println(twoSum([]int{3, 3}, 6))                // Output: [0, 1]
	fmt.Println(twoSum([]int{-1, -2, -3, -4, -5}, -8)) // Output: [2, 4]
}
