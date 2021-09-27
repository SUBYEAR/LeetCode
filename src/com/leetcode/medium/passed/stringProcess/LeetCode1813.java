package com.leetcode.medium.passed.stringProcess;

/**
 * 一个句子是由一些单词与它们之间的单个空格组成，且句子的开头和结尾没有多余空格。比方说，
 * "Hello World" ，"HELLO" ，"hello world hello world" 都是句子。每个单词都 只 包含大写和小写英文字母。
 * 如果两个句子 sentence1 和 sentence2 ，可以通过往其中一个句子插入一个任意的句子（可以是空句子）而得到另一个句子，
 * 那么我们称这两个句子是 相似的 。比方说，sentence1 = "Hello my name is Jane" 且 sentence2 = "Hello Jane" ，
 * 我们可以往 sentence2 中 "Hello" 和 "Jane" 之间插入 "my name is" 得到 sentence1 。
 * 给你两个句子 sentence1 和 sentence2 ，如果 sentence1 和 sentence2 是相似的，请你返回 true ，否则返回 false 。
 * <p></p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sentence-similarity-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1813 {
    public boolean areSentencesSimilar_reg(String sentence1, String sentence2) {
        if (sentence1.length() < sentence2.length()) {
            String tmp = sentence1;
            sentence1 = sentence2;
            sentence2 = tmp;
        }

        if (sentence1.matches(sentence2 + "(\\s[a-zA-Z]+)*")) {
            return true;
        } else if (sentence1.matches("([a-zA-Z]+\\s)*" + sentence2)) {
            return true;
        } else if (isMiddleMatch(sentence1, sentence2)) {
            return true;
        } else {
            return false;
        }
    }

    boolean isMiddleMatch(String s1, String s2) {
        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == ' ') {
                String reg = s2.substring(0, i) + "(\\s[a-zA-Z]+)*" + s2.substring(i);
                if (s1.matches(reg)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] arr1 = sentence1.split(" ");
        String[] arr2 = sentence2.split(" ");

        int length1 = arr1.length;
        int length2 = arr2.length;

        int left = 0, right = 0;
        //从左边遍历，遇到不同的词停止
        while (left < length1 && left < length2) {
            if (arr1[left].equals(arr2[left])) {
                left++;
            } else {
                break;
            }
        }

        if (left == Math.min(length1,length2)) return true;
        //从右边遍历，遇到不同的词停止
        while (right < length1 && right < length2) {
            if (arr1[length1-right-1].equals(arr2[length2 - right -1])) {
                right++;
            } else {
                break;
            }
        }
        if (right == Math.min(length1,length2)) return true;
        return (right+left) == Math.min(length1,length2);
    }
}
