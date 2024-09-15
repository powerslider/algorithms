package main

import "fmt"

type denomAmount struct {
	amountRemaining int
	denomIndex      int
}

func coinChange(n int) int {
	denoms := []int{25, 10, 5, 1}
	precomputedVals := make(map[denomAmount]int)
	return makeChange(n, denoms, 0, precomputedVals)
}

func makeChange(amount int, denoms []int, index int, vals map[denomAmount]int) int {
	// retrieve pre-computed value
	currentDenomAmount := denomAmount{amount, index}
	if val, found := vals[currentDenomAmount]; found {
		return val
	}

	// last denom remaining
	if index >= len(denoms)-1 {
		return 1
	}

	// get current denomination
	currentDenom := denoms[index]

	// calculate all the possible ways that current denomination can be part of the coin change
	ways := 0
	for i := 0; i*currentDenom <= amount; i++ {
		amountRemaining := amount - i*currentDenom
		ways += makeChange(amountRemaining, denoms, index+1, vals)
	}

	// remember how many ways the given amount and the given denomination can be part of the coin change
	vals[currentDenomAmount] = ways

	return ways
}

func main() {
	fmt.Println(coinChange(10))
}
