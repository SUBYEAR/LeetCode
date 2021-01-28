package com.leetcode;

import java.util.Arrays;

/**
 *给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。
 *
 * 选取一个删除索引序列，对于 A 中的每个字符串，删除对应每个索引处的字符。
 *
 * 比如，有 A = ["babca","bbazb"]，删除索引序列 {0, 1, 4}，删除后 A 为["bc","az"]。
 *
 * 假设，我们选择了一组删除索引 D，那么在执行删除操作之后，最终得到的数组的行中的每个元素都是按字典序排列的。
 *
 * 清楚起见，A[0] 是按字典序排列的（即，A[0][0] <= A[0][1] <= ... <= A[0][A[0].length - 1]），A[1] 是按字典序排列的（即，A[1][0] <= A[1][1] <= ... <= A[1][A[1].length - 1]），依此类推。
 *
 * 请你返回 D.length 的最小可能值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-columns-to-make-sorted-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode960 {
    public int minDeletionSize(String[] A) {
        int W = A[0].length();
        // 让 dp[k] 表示在输入为 [row[k:] for row in A] 时保存的列数，那么 dp[k] 的递推式显而易见。
        int[] dp = new int[W];
        Arrays.fill(dp, 1);
        for (int i = W-2; i >= 0; --i)
            search: for (int j = i+1; j < W; ++j) {
                for (String row: A)
                    if (row.charAt(i) > row.charAt(j))
                        continue search;

                dp[i] = Math.max(dp[i], 1 + dp[j]);
            }

        int kept = 0;
        for (int x: dp)
            kept = Math.max(kept, x);
        return W - kept;
    }
}
