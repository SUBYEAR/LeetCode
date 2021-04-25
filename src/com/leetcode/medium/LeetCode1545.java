package com.leetcode.medium;
/**
 * 给你两个正整数 n 和 k，二进制字符串  Sn 的形成规则如下：
 *
 * S1 = "0"
 * 当 i > 1 时，Si = Si-1 + "1" + reverse(invert(Si-1))
 * 其中 + 表示串联操作，reverse(x) 返回反转 x 后得到的字符串，而 invert(x) 则会翻转 x 中的每一位（0 变为 1，而 1 变为 0）。
 *
 * 例如，符合上述描述的序列的前 4 个字符串依次是：
 *
 * S1 = "0"
 * S2 = "011"
 * S3 = "0111001"
 * S4 = "011100110110001"
 * 请你返回  Sn 的 第 k 位字符 ，题目数据保证 k 一定在 Sn 长度范围以内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-kth-bit-in-nth-binary-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1545 {
    public char findKthBit(int n, int k) {
        String pre = "0";
        String cur = pre;
        for (int i = 1; i < n; i++) {
            String trans = reverseAndInvert(pre);
            cur =  pre + "1" + trans;
            pre = cur;
        }
        return cur.charAt(k - 1);
    }

    private String reverseAndInvert(String s) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '0') {
                c = '1';
            } else {
                c = '0';
            }
            b.append(c);
        }
        return b.reverse().toString();
    }
}
