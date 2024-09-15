package main

import "fmt"

// Given a string s, find the length of the longest substring without repeating characters.
func lengthOfLongestSubstring(s string) int {
	// initialize a map to store the last seen index of the current char.
	charMap := make(map[rune]int)
	left, maxLength := 0, 0

	for right, char := range s {
		// move the sliding window to lastSeenIndex + 1 when the current char has already been seen.
		if lastSeenIndex, found := charMap[char]; found && lastSeenIndex >= left {
			// update the start of the sliding window
			left = lastSeenIndex + 1
		}

		// update map with current char latest index.
		charMap[char] = right

		currentLength := right - left + 1
		if currentLength > maxLength {
			maxLength = currentLength
		}
	}

	return maxLength
}

func main() {
	fmt.Println(lengthOfLongestSubstring("abcabcbb")) // Output: 3
	fmt.Println(lengthOfLongestSubstring("bbbbb"))    // Output: 1
	fmt.Println(lengthOfLongestSubstring("pwwkew"))   // Output: 3
	fmt.Println(lengthOfLongestSubstring(""))         // Output: 0
	fmt.Println(lengthOfLongestSubstring(" "))        // Output: 1
	fmt.Println(lengthOfLongestSubstring("au"))       // Output: 2
	fmt.Println(lengthOfLongestSubstring("dvdf"))     // Output: 3
}
