package com.leetcode.medium.review.presum;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个只包含 0 和 1 的网格，找出其中角矩形的数量。
 *
 * 一个「角矩形」是由四个不同的在网格上的 1 形成的轴对称的矩形。注意只有角的位置才需要为 1。并且，4 个 1 需要是不同的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：grid =
 * [[1, 0, 0, 1, 0],
 *  [0, 0, 1, 0, 1],
 *  [0, 0, 0, 1, 0],
 *  [1, 0, 1, 0, 1]]
 * 输出：1
 * 解释：只有一个角矩形，角的位置为 grid[1][2], grid[1][4], grid[3][2], grid[3][4]。
 * 算法：
 * 我们用 count[i, j] 来记录 row[i] = row[j] = 1 的次数。当我们处理新的一行时，对于每一对 new_row[i] = new_row[j] = 1，我们添加 count[i, j] 到答案中，然后 count[i, j]++。
 *
 */
public class LeetCode750_W {
    public int countCornerRectangles(int[][] grid) { // 有点类似前缀和的思想
        Map<Integer, Integer> count = new HashMap();
        int ans = 0;
        for (int[] row: grid) {
            for (int c1 = 0; c1 < row.length; ++c1) {
                if (row[c1] == 1) {
                    for (int c2 = c1 + 1; c2 < row.length; ++c2)
                        if (row[c2] == 1) {
                            int pos = c1 * 200 + c2;
                            int c = count.getOrDefault(pos, 0);
                            ans += c;
                            count.put(pos, c + 1);
                        }
                }
            }
        }
        return ans;
    }
}

// 算法：
//
//我们能改进方法 1 中的方法吗？当一行有 XX 个 1 时，我们需要 O(X^2)O(X
//2
// ) 的时间来枚举每对 1。当 XX 很小时，这是可以接受的；但当 XX 很大时，这是较为耗时的操作。
//假设第一行的元素都是 1 时，f 指的是下一行和第一行所匹配 1 的数量。所能够构造角矩形的数量就是所匹配 1 的数量的对数，即 f * (f-1) / 2。我们可以使用一个集合和对每行进行简单线性扫描快速找到每个 f。
//class Solution {
//    public int countCornerRectangles(int[][] grid) {
//        List<List<Integer>> rows = new ArrayList();
//        int N = 0;
//        for (int r = 0; r < grid.length; ++r) {
//            rows.add(new ArrayList());
//            for (int c = 0; c < grid[r].length; ++c)
//                if (grid[r][c] == 1) {
//                    rows.get(r).add(c);
//                    N++;
//                }
//        }
//
//        int sqrtN = (int) Math.sqrt(N);
//        int ans = 0;
//        Map<Integer, Integer> count = new HashMap();
//
//        for (int r = 0; r < grid.length; ++r) {
//            if (rows.get(r).size() >= sqrtN) {
//                Set<Integer> target = new HashSet(rows.get(r));
//
//                for (int r2 = 0; r2 < grid.length; ++r2) {
//                    if (r2 <= r && rows.get(r2).size() >= sqrtN)
//                        continue;
//                    int found = 0;
//                    for (int c2: rows.get(r2))
//                        if (target.contains(c2))
//                            found++;
//                    ans += found * (found - 1) / 2;
//                }
//            } else {
//                for (int i1 = 0; i1 < rows.get(r).size(); ++i1) {
//                    int c1 = rows.get(r).get(i1);
//                    for (int i2 = i1 + 1; i2 < rows.get(r).size(); ++i2) {
//                        int c2 = rows.get(r).get(i2);
//                        int ct = count.getOrDefault(200*c1 + c2, 0);
//                        ans += ct;
//                        count.put(200*c1 + c2, ct + 1);
//                    }
//                }
//            }
//        }
//        return ans;
//    }
//}
