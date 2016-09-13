package com.ceco.algorithms.problems.stack;

import java.util.*;

/**
 * Example input:
 * 3
 * (a+(b*c))
 * ((a+b)*(z+x))
 * ((a+t)*((b+(a+c))^(c+d)))
 * <p>
 * Output:
 * abc*+
 * ab+zx+*
 * at+bac++cd+^*
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 07-Aug-2016
 */
public class ReversePolishNotaton {

    // operator -> precedence
    private static final Map<Character, Integer> OPS = new HashMap<Character, Integer>() {{
        put('+', 2);
        put('-', 2);
        put('*', 3);
        put('/', 3);
        put('^', 4);
    }};

    private static boolean hasHigherPrecedence(Character first, Character second) {
        return OPS.containsKey(second)
                && OPS.get(first) >= OPS.get(second);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numExpr = scanner.nextInt();
        scanner.nextLine();
        String[] exprArr = new String[numExpr];

        for (int i = 0; i < numExpr; i++) {
            exprArr[i] = scanner.nextLine();
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (String expr : exprArr) {
            System.out.println(postfix(stack, expr));
        }
    }

    public static String postfix(Deque<Character> stack, String expr) {
        char[] tokens = expr.toCharArray();
        StringBuilder output = new StringBuilder();
        for (Character token : tokens) {
            // if current token is an operator
            // loop through the stack and check if the
            // operator has higher precedence or it is not
            // compared with an operator => append to the
            // new expression and push the new operator at the
            // end so it will be poped on the next matched operator
            if (OPS.containsKey(token)) {
                while (!stack.isEmpty() && hasHigherPrecedence(token, stack.peek())) {
                    output.append(stack.pop());
                }
                stack.push(token);
            } else if (token.equals('(')) {
                stack.push(token);

                // if the closing parentesis is matched pop the stack until an
                // open parentesis is matched which will append the operands of the
                // enclosed expression, then pop the enclosing parentesis itself
            } else if (token.equals(')')) {
                while (!stack.peek().equals('(')) {
                    output.append(stack.pop());
                }
                stack.pop();
            } else {
                output.append(token);
            }
        }

        // if the stack is not empty, pop the rest to complete the
        // expression
        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }

        return output.toString();
    }
}
