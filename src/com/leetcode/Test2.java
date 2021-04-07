package com.leetcode;


public class Test2 {
    int flag = 0;
    int allLength = 0;
    public int encodeString(String s) {
        if (s.length() < 2) {
            return -1;
        }

        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                break;
            }
            len = len * 10 + s.charAt(i) - '0';
            if (i + len + 1 <= s.length()) {
                getSubLength(s, i + len + 1, len);
            }
        }

        return flag == 1 ? allLength : -1;
    }

    void getSubLength(String s, int start, int count) {
        if (start == s.length()) {
            flag++;
            allLength += count;
            return;
        }

        if (s.charAt(start) == '0') {
            return;
        }

        int length = 0;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                break;
            }
            length = length * 10 + s.charAt(i) - '0';
            if (i + length + 1 <= s.length()) {
                getSubLength(s, i + length + 1, count + length);
            }
        }
    }
}
