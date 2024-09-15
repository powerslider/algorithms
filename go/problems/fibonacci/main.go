package main

import (
	"fmt"
	"math"
	"time"
)

// Time: O(2^n), Space: O(n)
func fibRecursive(n int) int {
	defer timeTrack(time.Now(), "fibRecursive", n)

	if n == 0 || n == 1 {
		return n
	}

	return fibRecursive(n-1) + fibRecursive(n-2)
}

var memo = make(map[int]int)

// Time: O(n), Space: O(n)
func fibMemo(n int) int {
	defer timeTrack(time.Now(), "fibMemo", n)

	if n == 0 || n == 1 {
		return n
	}

	if val, found := memo[n]; found {
		return val
	}

	memo[n] = fibMemo(n-1) + fibMemo(n-2)

	return memo[n]
}

// Time: O(n), Space: O(1)
func fibIter(n int) int {
	defer timeTrack(time.Now(), "fibIter", n)

	if n == 0 || n == 1 {
		return n
	}

	prev := 0 // F(0)
	curr := 0 // F(1)

	for i := 2; i <= n; i++ {
		// compute next Fibonacci number
		next := prev + curr

		// shift the values for the next iteration
		prev = curr
		curr = next
	}

	return n // F(n)
}

func multiplyMatrix(a, b [2][2]int) [2][2]int {
	return [2][2]int{
		{
			a[0][0]*b[0][0] + a[0][1]*b[1][0],
			a[0][0]*b[0][1] + a[0][1]*b[1][1],
		},
		{
			a[1][0]*b[0][0] + a[1][1]*b[1][0],
			a[1][0]*b[0][1] + a[1][1]*b[1][1],
		},
	}
}

func powerMatrix(matrix [2][2]int, n int) [2][2]int {
	if n == 1 {
		return matrix
	}

	if n%2 == 0 {
		// n is even, so we square the matrix raised to n/2
		halfPower := powerMatrix(matrix, n/2)
		return multiplyMatrix(halfPower, halfPower)
	}

	// n is odd, reduce is by one to make it even
	return multiplyMatrix(matrix, powerMatrix(matrix, n-1))
}

func fibMatrixExp(n int) int {
	defer timeTrack(time.Now(), "fibMatrixExp", n)

	if n == 0 || n == 1 {
		return n
	}

	// the matrix representation of the Fibonacci recurrence relation
	matrix := [2][2]int{
		// F(n+1), F(n)
		{1, 1},
		// F(n), F(n-1)
		{1, 0},
	}

	// raise the matrix to the power of n-1
	resultMatrix := powerMatrix(matrix, n-1)

	// the top-left element of the resulting matrix if F(n)
	return resultMatrix[0][0]
}

func fibBinet(n int) int {
	defer timeTrack(time.Now(), "fibBinet", n)
	phi := (1 + math.Sqrt(5)) / 2
	result := (math.Pow(phi, float64(n)) - math.Pow(1-phi, float64(n))) / math.Sqrt(5)

	return int(math.Round(result))
}

func timeTrack(start time.Time, name string, currentN int) {
	elapsed := time.Since(start)
	fmt.Printf("%s for %d took %s\n", name, currentN, elapsed)
}

func main() {
	// Example: Print the Fibonacci numbers for n = 0 to 10
	for i := 0; i <= 10; i++ {
		fmt.Printf("FibonacciRecursive(%d) = %d\n", i, fibRecursive(i))
	}

	for i := 0; i <= 10; i++ {
		fmt.Printf("FibonacciMemoization(%d) = %d\n", i, fibMemo(i))
	}

	for i := 0; i <= 10; i++ {
		fmt.Printf("FibonacciMatrixExponentiation(%d) = %d\n", i, fibMatrixExp(i))
	}

	for i := 0; i <= 10; i++ {
		fmt.Printf("FibonacciBinetFormula(%d) = %d\n", i, fibBinet(i))
	}
}
