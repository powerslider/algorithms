package com.ceco.algorithms.problems.stack;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 01 May 2015
 */
public class ShuntingYard {

    // operator -> precedence
    private static final Map<Character, Integer> OPS = new HashMap<Character, Integer>() {{
        put('+', 2);
        put('-', 2);
        put('*', 3);
        put('/', 3);
        put('^', 4);
    }};

    private static void compute(String expr) {
        Stack<Character> postfixStack = new Stack<>();
        String reversePolishNotation = ReversePolishNotaton.postfix(postfixStack, expr);
        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

         for (Character token : reversePolishNotation.toCharArray()) {
            if (OPS.containsKey(token)) {
                operators.push(token);
            } else {
                values.push(Integer.parseInt(token.toString()));
            }

            if (!operators.isEmpty()) {
                Character op = operators.pop();
                if (op.equals('+')) {
                    values.push(values.pop() + values.pop());
                } else if (op.equals('-')) {
                    int first = values.pop();
                    int second = values.pop();
                    values.push(second - first);
                } else if (op.equals('*')) {
                    values.push(values.pop() * values.pop());
                } else if (op.equals('/')) {
                    values.push(values.pop() / values.pop());
                }
            }

        }
        System.out.println(values.pop());
    }

    public static void main(String[] args) {
        compute("(2*2)/(3-(6+5))-4");
    }
}
