package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回所有长度为 n 且满足其每两个连续位上的数字之间的差的绝对值为 k 的 非负整数 。
 *
 * 请注意，除了 数字 0 本身之外，答案中的每个数字都 不能 有前导零。例如，01 有一个前导零，所以是无效的；但 0 是有效的。
 *
 * 你可以按 任何顺序 返回答案。
 *
 * 提示：
 *
 * 2 <= n <= 9
 * 0 <= k <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/numbers-with-same-consecutive-differences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode967 {
    List<Integer> res = new ArrayList<>();
    public int[] numsSameConsecDiff(int n, int k) {
        backtrack(n, k, new ArrayList<>());
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    void backtrack(int n , int k, List<Integer> path) {
        if (path.size() == n) {
            int num = 0;
            for (int i = 0; i < path.size(); i++) {
                num = num * 10 + path.get(i);
            }
            res.add(num);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (path.size() == 0 && i == 0) {
                continue;
            }

            if (path.size() > 0 && Math.abs(i - path.get(path.size() - 1)) != k) {
                continue;
            }
            path.add(i);
            backtrack(n, k, path);
            path.remove(path.size() - 1);
        }
    }
}
