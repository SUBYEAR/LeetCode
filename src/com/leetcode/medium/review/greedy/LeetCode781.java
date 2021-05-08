package com.leetcode.medium.review.greedy;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 两只相同颜色的兔子看到的其他同色兔子数必然是相同的。反之，若两只兔子看到的其他同色兔子数不同，那么这两只兔子颜色也不同。
     * 因此，将 answers 中值相同的元素分为一组，对于每一组，计算出兔子的最少数量，然后将所有组的计算结果累加，就是最终的答案。
     * 例如，现在有 13 只兔子回答 5。假设其中有一只红色的兔子，那么森林中必然有 6 只红兔子。再假设其中还有一只蓝色的兔子，
     * 同样的道理森林中必然有 6 只蓝兔子。为了最小化可能的兔子数量，我们假设这 12 只兔子都在这 13 只兔子中。那么还有一只额外的兔子回答 5，
     * 这只兔子只能是其他的颜色，这一颜色的兔子也有 6 只。因此这种情况下最少会有 18 只兔子。
     * 一般地，如果有 x 只兔子都回答 y，则至少有 [x/ (y+1)]种不同的颜色，且每种颜色有 y+1 只兔子，因此兔子数至少为[x/ (y+1)] * (y+1）
     */
    public int numRabbits_(int[] answers) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int y : answers) {
            count.put(y, count.getOrDefault(y, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int y = entry.getKey(), x = entry.getValue();
            ans += (x + y) / (y + 1) * (y + 1);
        }
        return ans;
    }
}
