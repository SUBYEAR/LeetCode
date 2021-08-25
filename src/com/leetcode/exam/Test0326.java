package com.leetcode.exam;

public class Test0326 {
    // #Title_2 字符编码无歧义:在字符串中插入校验码每个校验码后跟对应数字的个数,校验码大于0且无前导0
    // 如109something就是有歧义的, 3000无歧义返回3
    int flag = 0;
    int allLength = 0;
    public int getLength(String encodingString) {
        if (encodingString.length() < 2) {
            return -1;
        }

        int len = 0;
        for (int i = 0; i < encodingString.length(); i++) {
            if (encodingString.charAt(i) >= 'a' && encodingString.charAt(i) <= 'z') {
                break;
            }
            len = len * 10 + encodingString.charAt(i) - '0';
            if (i + len + 1 <= encodingString.length()) {
                getSubLength(encodingString, i + len + 1, len);
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
