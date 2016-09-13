package com.ceco.algorithms.problems.stack;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

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
        Deque<Character> postfixStack = new ArrayDeque<>();
        String reversePolishNotation = ReversePolishNotaton.postfix(postfixStack, expr);
        Deque<Integer> values = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

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
