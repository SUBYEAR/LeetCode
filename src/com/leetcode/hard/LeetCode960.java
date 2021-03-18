package com.leetcode.hard;

import java.util.Arrays;

/**
 *给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。
 * 选取一个删除索引序列，对于 A 中的每个字符串，删除对应每个索引处的字符。
 * 比如，有 A = ["babca","bbazb"]，删除索引序列 {0, 1, 4}，删除后 A 为["bc","az"]。
 * 清楚起见，A[0] 是按字典序排列的（即，A[0][0] <= A[0][1] <= ... <= A[0][A[0].length - 1]），A[1] 是按字典序排列的
 * （即，A[1][0] <= A[1][1] <= ... <= A[1][A[1].length - 1]），依此类推。
 * 请你返回 D.length 的最小可能值。
 *
 * 假设我们一定保存第一列 C，那么保存的下一列 D 就必须保证每行都是字典有序的，也就是 C[i] <= D[i]。
 * 那么我们就可以删除 C 和 D 之间的所有列。
 *
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

// C++解法 直接求最长的，符合条件的，非连续的，升序的，子串的，max长度
// class Solution
//{
//public:
//    int minDeletionSize(vector<string>& strs)
//    {
//        int word_len = strs[0].size();
//        vector<int> starts_with(word_len, 1);   //以当前index为首字母的子串，升序，最长的长度
//        for (int L = word_len - 2; L > -1; L --)    //子串的左端点（实指）
//        {
//            for (int R = L + 1; R < word_len; R ++) //子串的右端点（实指）
//            {
//                bool all_ok = true;           //所有的单词都要满足升序
//                for(string & w: strs)
//                {
//                    if (w[L] > w[R])
//                    {
//                        all_ok = false;
//                        break;
//                    }
//                }
//                if (all_ok == true)
//                    starts_with[L] = std::max(starts_with[L], starts_with[R] + 1);
//            }
//        }
//        return word_len - *max_element(starts_with.begin(), starts_with.end());
//    }
//};
