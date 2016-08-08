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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numExpr = scanner.nextInt();
        scanner.nextLine();
        String[] exprArr = new String[numExpr];

        for (int i = 0; i < numExpr; i++) {
            exprArr[i] = scanner.nextLine();
        }

        for (String expr : exprArr) {
            Matcher matcher = ARITH_EXPR_PATTERN.matcher(expr);
            if (matcher.find()) {
                String firstOperandStr = matcher.group(1);
                String operator = matcher.group(2);
                String secondOperandStr = matcher.group(3);

                String newExpr;
                if (firstOperandStr.length() == 1 || secondOperandStr.length() == 1) {
                    newExpr = singleDigitCase(firstOperandStr, secondOperandStr, operator);
                } else if (operator.equals("*")) {
                    newExpr = multiplication(firstOperandStr, secondOperandStr, operator);
                } else {
                    newExpr = additionOrSubtraction(firstOperandStr, secondOperandStr, operator);
                }
                System.out.println(newExpr);
            }
        }
    }

    private static String multiplication(String firstOperandStr, String secondOperandStr, String operator) {
        StringJoiner joiner = new StringJoiner("\n");

        int firstOperand = Integer.parseInt(firstOperandStr);
        int secondOperand = Integer.parseInt(secondOperandStr);

        int secondOperandLength = secondOperandStr.length();
        int firstOperandLength = firstOperandStr.length();

        int diff = secondOperandLength - firstOperandLength;

        int paddingCount = diff > 0
                ? secondOperandLength + 1 - secondOperandLength / 2
                : firstOperandLength + 1 - firstOperandLength / 2;

        joiner
                .add(whitespaces(paddingCount + 1) + firstOperandStr)
                .add(whitespaces(paddingCount - diff) + operator + secondOperandStr);

        int newSecondOperand = secondOperand;
        String currentResultStr = null;
        for (int i = 0; newSecondOperand != 0; i++) {
            int currentDigit = newSecondOperand % 10;
            int currentResult = currentDigit * firstOperand;
            currentResultStr = String.valueOf(currentResult);
            if (i == 0) {
                int dashesCount = longest(
                        firstOperandStr,
                        secondOperandStr,
                        currentResultStr);
                joiner.add(whitespaces(paddingCount - diff) + dashes(dashesCount + 1));
                diff--;
            }
            joiner.add(whitespaces(paddingCount - (diff++)) + currentResult);

            newSecondOperand /= 10;
        }

        int secondDashesCount = currentResultStr.length() + currentResultStr.length() / 2 + 1;
        joiner.add(dashes(secondDashesCount));

        return joiner.toString();
    }

    private static String additionOrSubtraction(String firstOperandStr, String secondOperandStr, String operator) {
        StringJoiner joiner = new StringJoiner("\n");
        int result = 0;
        int firstOperand = Integer.parseInt(firstOperandStr);
        int secondOperand = Integer.parseInt(secondOperandStr);
        switch (operator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
        }
        String resultStr = String.valueOf(result);

        int dashesCount = longest(firstOperandStr, secondOperandStr, resultStr);

        int firstOperandLength = firstOperandStr.length();
        int secondOperandLength = secondOperandStr.length();

        int diff = Math.abs(firstOperandLength - secondOperandLength);

        if (firstOperandLength > secondOperandLength) {
            joiner
                    .add(firstOperandStr)
                    .add(whitespaces(diff - 1) + operator + secondOperandStr);
        } else if (firstOperandLength == secondOperandLength) {
            joiner
                    .add(" " + firstOperandStr)
                    .add(operator + secondOperandStr);
        } else {
            joiner
                    .add(whitespaces(diff + 1) + firstOperandStr)
                    .add(operator + secondOperandStr);
        }

        int longestOperand = Math.max(firstOperandLength, secondOperandLength);
        resultStr = (longestOperand == resultStr.length()) ? (" " + resultStr) : resultStr;
        joiner
                .add(dashes(dashesCount + 1))
                .add(resultStr + "\n");

        return joiner.toString();
    }

    private static String singleDigitCase(String firstOperandStr, String secondOperandStr, String operator) {
        StringJoiner joiner = new StringJoiner("\n");

        int firstOperandLength = firstOperandStr.length();
        int secondOperandLength = secondOperandStr.length();

        String singleDigitOperationStr = "";
        int firstOperand = Integer.parseInt(firstOperandStr);
        int secondOperand = Integer.parseInt(secondOperandStr);
        switch (operator) {
            case "+":
                singleDigitOperationStr = String.valueOf(firstOperand + secondOperand);
                break;
            case "-":
                singleDigitOperationStr = String.valueOf(firstOperand - secondOperand);
                break;
            case "*":
                singleDigitOperationStr = String.valueOf(firstOperand * secondOperand);
                break;
        }

        int singleDigitOperationNumLength = singleDigitOperationStr.length();
        int longestLength = 0;
        if (firstOperandLength == 1) {
            longestLength = Math.max(secondOperandLength, singleDigitOperationNumLength);
            joiner
                    .add(whitespaces(longestLength) + firstOperandStr)
                    .add(whitespaces(Math.abs(secondOperandLength - singleDigitOperationNumLength)) + operator + secondOperandStr);
        } else if (secondOperandLength == 1) {
            longestLength = Math.max(firstOperandLength, singleDigitOperationNumLength);
            joiner
                    .add(whitespaces(Math.abs(firstOperandLength - singleDigitOperationNumLength)) + firstOperandStr)
                    .add(whitespaces(longestLength - 2) + operator + secondOperandStr);
        }

        joiner
                .add(dashes(longestLength))
                .add(singleDigitOperationStr + "\n");

        return joiner.toString();
    }

    private static String whitespaces(int count) {
        return generate(count, " ");
    }

    private static String dashes(int count) {
        return generate(count, "-");
    }

    private static String generate(int count, String unit) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(unit);
        }
        return builder.toString();
    }

    private static int longest(String firstOperandStr, String secondOperandStr, String resultStr) {
        int longestOperand = Math.max(
                firstOperandStr.length(), secondOperandStr.length());
        return Math.max(resultStr.length(), longestOperand);
    }
}
