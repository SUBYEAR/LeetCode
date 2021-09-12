package com.leetcode.medium.review.stringprocessing;

/**
 * 给你两个字符串 word1 和 word2 。你需要按下述方式构造一个新字符串 merge ：如果 word1 或 word2 非空，选择 下面选项之一 继续操作：
 *
 * 如果 word1 非空，将 word1 中的第一个字符附加到 merge 的末尾，并将其从 word1 中移除。
 * 例如，word1 = "abc" 且 merge = "dv" ，在执行此选项操作之后，word1 = "bc" ，同时 merge = "dva" 。
 * 如果 word2 非空，将 word2 中的第一个字符附加到 merge 的末尾，并将其从 word2 中移除。
 * 例如，word2 = "abc" 且 merge = "" ，在执行此选项操作之后，word2 = "bc" ，同时 merge = "a" 。
 * 返回你可以构造的字典序 最大 的合并字符串 merge 。
 *
 * 长度相同的两个字符串 a 和 b 比较字典序大小，如果在 a 和 b 出现不同的第一个位置，a 中字符在字母表中的出现顺序位于 b 中相应字符之后，
 * 就认为字符串 a 按字典序比字符串 b 更大。例如，"abcd" 按字典序比 "abcc" 更大，因为两个字符串出现不同的第一个位置是第四个字符，而 d 在字母表中的出现顺序位于 c 之后。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-merge-of-two-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1754 {
    int index1 = 0;
    int index2 = 0;
    public String largestMerge(String word1, String word2) { // cbcabaaaaa
        int len = word1.length() + word2.length();
        StringBuilder ans = new StringBuilder(len);
        while (index1 < word1.length() && index2 < word2.length()) {
            int diff = word1.charAt(index1) - word2.charAt(index2);
            if (diff == 0) { // 向后找到第一个不等的地方
                diff = word1.substring(index1).compareTo(word2.substring(index2));
            }
            if (diff > 0) {
                ans.append(word1.charAt(index1));
                index1++;
            } else {
                ans.append(word2.charAt(index2));
                index2++;
            }
        }
        if (index1 != word1.length()) {
            ans.append(word1.substring(index1));
        }
        if (index2 != word2.length()) {
            ans.append(word2.substring(index2));
        }
        return ans.toString();
    }
}
