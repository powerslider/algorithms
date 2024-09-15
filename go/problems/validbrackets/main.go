package main

import "fmt"

func areBracketsBalanced(expr string) bool {
	// initialize a map <closing bracket> -> <opening bracket>
	bracketMap := map[rune]rune{
		')': '(',
		'}': '{',
		']': '[',
	}

	stack := make([]rune, 0)

	for _, char := range expr {
		switch char {
		case '(', '{', '[':
			// push the opening bracket to the stack
			stack = append(stack, char)
		case ')', '}', ']':
			// if the stack is empty or the top element is different from the corresponding closing bracket, return invalid expr
			topIndex := len(stack) - 1
			if len(stack) == 0 || stack[topIndex] != bracketMap[char] {
				return false
			}

			// pop the top of the stack containing the closing bracket
			stack = stack[:topIndex]
		}
	}

	// the expression is valid once all chars are popped from the stack
	return len(stack) == 0
}

func main() {
	fmt.Println(areBracketsBalanced("()[]{}")) // Output: true
	fmt.Println(areBracketsBalanced("(]"))     // Output: false
}
