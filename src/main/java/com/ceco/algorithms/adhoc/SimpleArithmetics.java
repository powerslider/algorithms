package com.ceco.algorithms.adhoc;

import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 07-Aug-2016
 */
public class SimpleArithmetics {

    private static final Pattern ARITH_EXPR_PATTERN = Pattern.compile("(\\d+)([\\+\\-\\*])(\\d+)");

    private static String generate(int count, String unit) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(unit);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numExpr = scanner.nextInt();
        scanner.nextLine();
        String[] exprArr = new String[numExpr];

        for (int i = 0; i < numExpr; i++) {
            exprArr[i] = scanner.nextLine();
        }

        for (String expr : exprArr) {
            StringJoiner joiner = new StringJoiner("\n");

            Matcher matcher = ARITH_EXPR_PATTERN.matcher(expr);
            if (matcher.find()) {
                String firstOperandStr = matcher.group(1);
                int firstOperand = Integer.parseInt(firstOperandStr);

                String operator = matcher.group(2);

                String secondOperandStr = matcher.group(3);
                int secondOperand = Integer.parseInt(secondOperandStr);

                if (operator.equals("*")) {
                    int diff = secondOperandStr.length() - firstOperandStr.length();
                    int paddingCount = diff > 0
                            ? secondOperandStr.length() + 1 - secondOperandStr.length() / 2
                            : firstOperandStr.length() + 1 - firstOperandStr.length() / 2;

                    joiner
                            .add(generate(paddingCount, " ") +  firstOperandStr)
                            .add(generate(paddingCount - diff, " ") + operator + secondOperandStr)
                            .add(generate(secondOperandStr.length() + 1, " "));

                    int newSecondOperand = secondOperand;
                    while (newSecondOperand != 0) {
                        int currentDigit = newSecondOperand % 10;
                        int currentResult = currentDigit * firstOperand;

                        newSecondOperand /= 10;
                    }

                } else {
                    int result = 0;
                    switch (operator) {
                        case "+":
                            result = firstOperand + secondOperand;
                            break;
                        case "-":
                            result = firstOperand - secondOperand;
                            break;
                    }
                    String resultStr = String.valueOf(result);

                    int longestOperand = Math.max(
                            firstOperandStr.length(), secondOperandStr.length());
                    int dashesCount = Math.max(resultStr.length(), longestOperand);

                    joiner
                            .add(firstOperandStr)
                            .add(operator + secondOperandStr)
                            .add(generate(dashesCount + 1, "-"))
                            .add(resultStr + "\n");
                }
                System.out.println(joiner.toString());
            }

        }
    }
}
