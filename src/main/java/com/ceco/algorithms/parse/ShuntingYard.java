package com.ceco.algorithms.parse;


import com.ceco.algorithms.datastructure.stack.ArrayStack;
import com.ceco.algorithms.datastructure.stack.LinkedStack;
import com.ceco.algorithms.datastructure.stack.Stack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * <p/>
 * Date added 1/5/15
 */
public class ShuntingYard {

    private enum Operator {

        ADD(1), SUBTRACT(2), MULTIPLY(3), DIVIDE(4);

        final int precedence;

        Operator(int p) {
            precedence = p;
        }
    }

    private static Map<Character, Operator> ops = new HashMap<Character, Operator>() {{
        put('+', Operator.ADD);
        put('-', Operator.SUBTRACT);
        put('*', Operator.MULTIPLY);
        put('/', Operator.DIVIDE);
    }};

    private static boolean hasHigherPrecedence(Character lowerPrecOp, Character higherPrecOp) {
        return ops.containsKey(higherPrecOp)
                && ops.get(higherPrecOp).precedence >= ops.get(lowerPrecOp).precedence;
    }

    private static String postfix(String expr) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack  = new ArrayStack<>();

        for (Character token : expr.toCharArray()) {
            // operator
            if (ops.containsKey(token)) {
                while (!stack.isEmpty() && hasHigherPrecedence(token, stack.peek())) {
                    output.append(stack.pop());
                }
                stack.push(token);

            // left parenthesis
            } else if (token.equals('(')) {
                stack.push(token);

            // right parenthesis
            } else if (token.equals(')')) {
                while (!stack.peek().equals('(')) {
                    output.append(stack.pop());
                }
                stack.pop();

            // digit
            } else {
                output.append(token);
            }
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop());
        }

        return output.toString();
    }

    private static void compute(String expr) {
        String reversePolishNotation = postfix(expr);
        Stack<Integer> values = new LinkedStack<>();
        Stack<Character> operators = new LinkedStack<>();

         for (Character token : reversePolishNotation.toCharArray()) {
            if (ops.containsKey(token)) {
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
