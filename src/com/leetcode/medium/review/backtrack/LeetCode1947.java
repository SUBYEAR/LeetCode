package com.leetcode.medium.review.backtrack;

/**
 * 有一份由 n 个问题组成的调查问卷，每个问题的答案要么是 0（no，否），要么是 1（yes，是）。
 *
 * 这份调查问卷被分发给 m 名学生和 m 名导师，学生和导师的编号都是从 0 到 m - 1 。学生的答案用一个二维整数数组 students 表示，
 * 其中 students[i] 是一个整数数组，包含第 i 名学生对调查问卷给出的答案（下标从 0 开始）。
 * 导师的答案用一个二维整数数组 mentors 表示，其中 mentors[j] 是一个整数数组，包含第 j 名导师对调查问卷给出的答案（下标从 0 开始）。
 *
 * 每个学生都会被分配给 一名 导师，而每位导师也会分配到 一名 学生。配对的学生与导师之间的兼容性评分等于学生和导师答案相同的次数。
 *
 * 例如，学生答案为[1, 0, 1] 而导师答案为 [0, 0, 1] ，那么他们的兼容性评分为 2 ，因为只有第二个和第三个答案相同。
 * 请你找出最优的学生与导师的配对方案，以 最大程度上 提高 兼容性评分和 。
 *
 * 给你 students 和 mentors ，返回可以得到的 最大兼容性评分和 。
 * 提示：
 *
 * m == students.length == mentors.length
 * n == students[i].length == mentors[j].length
 * 1 <= m, n <= 8
 * students[i][k] 为 0 或 1
 * mentors[j][k] 为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-compatibility-score-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1947 {
    public int maxCompatibilitySum(int[][] students, int[][] mentors) { // 与1879题的解法类似
        // 用长度为n的二进制数mask表示数组nums2中的数被选择的状态如果mask从低到高的第i位为1，说明 nums2[i] 已经被选择，否则说明其未被选择。
        // f[mask]表示当选择了数组nums2中的元素状态为mask，并且选择了数组nums1的前count(mask)个元素
        int m = students.length;
        int[] dp = new int[1 << m];
        int[][] sameArr = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                sameArr[i][j] = calc(students[i], mentors[j]);
            }
        }

        for (int mask = 1; mask < dp.length; mask++) {
            for (int i = 0; i < m; i++) {
                if ((mask & (1 << i)) != 0) {
                    int same = sameArr[Integer.bitCount(mask) - 1][i];
                    dp[mask] = Math.max(dp[mask], same + dp[mask ^ (1 << i)]);
                }
            }
        }
        return dp[(1 << m) - 1];
    }

    private int calc(int[] arr1, int[] arr2) {
        int same = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] == arr2[i]) {
                ++same;
            }
        }
        return same;
    }

    // 回溯的解法
    int m;      //学生和老师的人数
    int n;      //n个问题
    int [][] score;     //学生和老师的匹配得分
    //-------- 回溯用到的
    boolean [] used;
    int max_;
    int cur;

    public int maxCompatibilitySum_(int[][] students, int[][] mentors) {
        this.m = students.length;
        this.n = students[0].length;
        this.score = new int [m][m];
        this.used = new boolean [m];
        this.max_ = 0;
        this.cur = 0;

        for (int si = 0; si < m; si ++)
        {
            for (int mj = 0; mj < m; mj ++)
            {
                int cur_score = 0;
                for (int ni = 0; ni < n; ni ++)
                    if (students[si][ni] == mentors[mj][ni])
                        cur_score ++;
                score[si][mj] = cur_score;
            }
        }

        backtrace(0);
        return this.max_;
    }

    public void backtrace(int si)
    {
        if (si == m)
        {
            max_ = Math.max(max_, cur);
            return ;
        }
        for (int mj = 0; mj < m; mj ++)
        {
            if (!used[mj])
            {
                used[mj] = true;
                cur += score[si][mj];
                backtrace(si + 1);
                cur -= score[si][mj];
                used[mj] = false;
            }
        }
    }
}
