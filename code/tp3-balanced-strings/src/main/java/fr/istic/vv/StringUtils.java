package fr.istic.vv;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        if (str == null) {
            return false;
        }
        LinkedList <Character> list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                list.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (list.isEmpty() || !matches(list.pop(), c)) {
                    return false;
                }
            }
            
        }
      return list.isEmpty();
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}