package com.ceco.algorithms.problems.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * @see <a href="https://www.hackerrank.com/challenges/balanced-brackets">
 *          Balanced Brackets
 *     </a>
 *
 * Example input:
 * 5
 * }][}}(}][))]
 * [](){()}
 * ()
 * ({}([][]))[]()
 * {)[](}]}]}))}(())(
 *
 * Output:
 * NO
 * YES
 * YES
 * YES
 * NO
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 11-Aug-2016
 */
public class BalancedBrackets {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numExpr = scanner.nextInt();
        scanner.nextLine();

        String[] bracketsExprArr = new String[numExpr];
        for (int i = 0; i < numExpr; i++) {
            bracketsExprArr[i] = scanner.nextLine();
        }

        for (String expr : bracketsExprArr) {
            if (areBracketsBalanced(expr)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean areBracketsBalanced(String expr) {
        Stack<Character> stack = new Stack<>();
        for (Character c : expr.toCharArray()) {
            if (c == '[' || c == '{' || c == '(')  {
                stack.push(c);
            } else if (c == ']' || c == '}' || c == ')') {
                if (stack.isEmpty()) return false;

                Character openingBracket = stack.pop();
                switch (c) {
                    case ']':
                        if (openingBracket != '[') return false;
                        break;
                    case '}':
                        if (openingBracket != '{') return false;
                        break;
                    case ')':
                        if (openingBracket != '(') return false;
                        break;
                }
            }
        }

        return stack.isEmpty();
    }
}
