package com.ceco.algorithms.problems.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/simple-text-editor">
 *          Simple Text Editor
 *     </a>
 *
 * Example input:
 * 8
 * 1 abc
 * 3 3
 * 2 3
 * 1 xy
 * 3 2
 * 4
 * 4
 * 3 1
 *
 * Output:
 * c
 * y
 * a
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 14-Aug-2016
 */
public class SimpleTextEditor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numQueries = scanner.nextInt();
        scanner.nextLine();

        String editorText = "";
        Deque<String> stateStack = new ArrayDeque<>();

        for (int i = 0; i < numQueries; i++) {
            String[] queryParts = scanner.nextLine().split("\\s");
            int queryType = Integer.parseInt(queryParts[0]);
            String queryInput = queryParts[queryParts.length - 1];
            switch (queryType) {
                case 1:
                    stateStack.push(editorText);
                    editorText += queryInput;
                    break;
                case 2:
                    stateStack.push(editorText);
                    int strLen = editorText.length() - Integer.parseInt(queryInput);
                    editorText = editorText.substring(0, strLen);
                    break;
                case 3:
                    int charIdx = Integer.parseInt(queryInput);
                    System.out.println(editorText.charAt(charIdx - 1));
                    break;
                case 4:
                    editorText = stateStack.pop();
                    break;
            }
        }
    }
}
