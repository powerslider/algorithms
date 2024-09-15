package main

import "fmt"

// Given two strings s1 and s2, return true if s2 is an anagram of s1, and false otherwise.
func isAnagram(s1, s2 string) bool {
	charFreqMap := make(map[rune]int)

	for _, char := range s1 {
		charFreqMap[char]++
	}

	for _, char := range s1 {
		charFreqMap[char]--
		if charFreqMap[char] < 0 {
			return false
		}
	}

	return true
}

func main() {
	fmt.Println(isAnagram("anagram", "nagaram"))
}
