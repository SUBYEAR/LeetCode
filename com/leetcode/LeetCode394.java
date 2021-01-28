package com.leetcode;

import java.util.Collections;
import java.util.LinkedList;

public class LeetCode394 {
    int index;
    public String decodeString(String s) {
        LinkedList<String> stack = new LinkedList<>();
        index = 0;
        while (index < s.length()) {
            char curChar = s.charAt(index);
            if (Character.isDigit(curChar)) {
                String digits = getDigits(s);
                stack.addLast(digits);
            } else if (Character.isLetter(curChar) || curChar == '[') {
                stack.addLast(String.valueOf(curChar));
                ++index;
            } else {
                ++index;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stack.peekLast())) {
                    sub.addLast(stack.removeLast());
                }
                Collections.reverse(sub);

                stack.removeLast();

                int times = Integer.parseInt(stack.removeLast());
                StringBuffer temp =  new StringBuffer();
                String o = getString(sub);
                while (times-- > 0) {
                    temp.append(o);
                }
                stack.addLast(temp.toString());

            }
        }

        return getString(stack);
    }

    private String getDigits(String s) {
        StringBuffer  result = new StringBuffer ();
        while (Character.isDigit(s.charAt(index))) {
            result.append(s.charAt(index));
            ++index;
        }
        return result.toString();
    }

    private String getString(LinkedList<String> lists) {
        StringBuffer  result = new StringBuffer ();
        for (String s : lists) {
            result.append(s);
        }
        return result.toString();
    }
}
