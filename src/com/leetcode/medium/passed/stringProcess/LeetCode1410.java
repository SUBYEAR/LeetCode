package com.leetcode.medium.passed.stringProcess;

/**
 * 「HTML 实体解析器」 是一种特殊的解析器，它将 HTML 代码作为输入，并用字符本身替换掉所有这些特殊的字符实体。
 *
 * HTML 里这些特殊字符和它们对应的字符实体包括：
 *
 * 双引号：字符实体为 &quot; ，对应的字符是 " 。
 * 单引号：字符实体为 &apos; ，对应的字符是 ' 。
 * 与符号：字符实体为 &amp; ，对应对的字符是 & 。
 * 大于号：字符实体为 &gt; ，对应的字符是 > 。
 * 小于号：字符实体为 &lt; ，对应的字符是 < 。
 * 斜线号：字符实体为 &frasl; ，对应的字符是 / 。
 * 给你输入字符串 text ，请你实现一个 HTML 实体解析器，返回解析器解析后的结果。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/html-entity-parser
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1410 {
    String[] encode = new String[] {"&quot;", "&apos;", "&amp;", "&gt;", "&lt;", "&frasl;"};
    String[] decode = new String[] {"\"", "'", "&", ">", "<", "/"};
    public String entityParser(String text) {
        int index = 0;
        int invalid = text.length() + 1;
        StringBuilder s = new StringBuilder(text);
        while (index < text.length()) {
            int left = invalid;
            int idx = 0;
            for (int i = 0; i < encode.length; i++) {
                int matchPos = s.indexOf(encode[i], index);
                if (matchPos >= 0) {
                    if (left > matchPos) {
                        left = matchPos;
                        idx = i;
                    }
                }
            }
            if (left == invalid) {
                break;
            }
            s.delete(left, left + encode[idx].length());
            s.insert(left, decode[idx]);
            index = left + decode[idx].length();
        }

        return s.toString();
    }
}
