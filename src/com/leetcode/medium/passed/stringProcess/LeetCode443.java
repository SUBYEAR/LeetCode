package com.leetcode.medium.passed.stringProcess;

/**
 * 给你一个字符数组 chars ，请使用下述算法压缩：
 *
 * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
 *
 * 如果这一组长度为 1 ，则将字符追加到 s 中。
 * 否则，需要向 s 追加字符，后跟这一组的长度。
 * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
 *
 * 请在 修改完输入数组后 ，返回该数组的新长度。
 *
 * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-compression
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode443 {
    public int compress(char[] chars) {
        int len = chars.length;
        StringBuilder list = new StringBuilder(len);
        int i = 0;
        int prePos = 0;
        while (i < len) {
            while (i + 1 < len && chars[i] == chars[i + 1]) {
                i++;
            }
            int cnt = i - prePos + 1;
            list.append(chars[i]);
            if (cnt > 1) {
                list.append(cnt);
            }
            i++;
            prePos = i;
        }

        for (int j = 0; j < list.length(); j++) {
            chars[j] = list.charAt(j);
        }
        return list.length();
    }
}
