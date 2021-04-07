package com.leetcode.medium;

import java.util.HashMap;

/**
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。
 *
 * 返回森林中兔子的最少数量。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rabbits-in-forest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode781 {
    public int numRabbits(int[] answers) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : answers) {
            if (num == 0) {
                res += 1;
                continue;
            }
            int cnt = map.getOrDefault(num, 0);
            ++cnt;
            if (cnt == 1) { // 第一次出现
                res = res + num + 1;
            } else if (cnt > num) {
                cnt = 0;
            }
            map.put(num, cnt);
        }
        return res;
    }
}
