package com.leetcode.medium.review.bfs;

import java.util.*;

/**
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：
 * 例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 *
 *  
 *
 * 示例 1:
 *
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/open-the-lock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode752 {
    public int openLock(String[] deadends, String target) {
        final String initStr = "0000";
        List<String> deadDigits = Arrays.asList(deadends);
        if (deadDigits.contains(initStr)) {
            return -1;
        }

        Queue<String> q = new LinkedList<>();
        Set<String > seen = new HashSet<>();
        seen.add(initStr);
        q.add(initStr);
        int step = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String cur = q.poll();
                if (target.equals(cur)) {
                    return step;
                }

                List<String> nextLevel = getNextLevel(cur, seen);
                for (String next : nextLevel) {
                    if (deadDigits.contains(next) || seen.contains(next)) {
                        continue;
                    }
                    seen.add(next);
                    q.offer(next);
                }
            }
            ++step;
        }
        return -1;
    }

    public List<String> getNextLevel(String s, Set<String > seen) {
        List<String> res = new LinkedList<>();
        char[] chars = s.toCharArray();
        int length = chars.length;
        char[] add = Arrays.copyOf(chars, length);
        char[] minus = Arrays.copyOf(chars, length);
        for (int i = 0; i < length; i++) {
            char num = chars[i];
            add[i] = num > '8' ? '0' : ++add[i];
            minus[i] = num < '1' ? '9' : --minus[i];
            String s1 = new String(add);
            String s2 = new String(minus);
            if (!seen.contains(s1)) res.add(s1);
            if (!seen.contains(s2)) res.add(s2);
            add[i] = num;
            minus[i] = num;
        }
        return res;
    }
}
