package main

import "fmt"

// Find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
func maxSubarray(nums []int) int {
	currentSum, maxSum := nums[0], nums[0]

	for _, num := range nums {
		currentSum = max(num, currentSum+num)
		maxSum = max(maxSum, currentSum)
	}

	return maxSum
}

func main() {
	fmt.Println(maxSubarray([]int{-2, 1, -3, 4, -1, 2, 1, -5, 4})) // Output: 6
}
