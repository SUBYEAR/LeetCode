package com.leetcode.hard.segmentTree;

import java.util.*;

/**
 * 你打算构建一些障碍赛跑路线。给你一个 下标从 0 开始 的整数数组 obstacles ，数组长度为 n ，其中 obstacles[i] 表示第 i 个障碍的高度。
 *
 * 对于每个介于 0 和 n - 1 之间（包含 0 和 n - 1）的下标  i ，在满足下述条件的前提下，请你找出 obstacles 能构成的最长障碍路线的长度：
 *
 * 你可以选择下标介于 0 到 i 之间（包含 0 和 i）的任意个障碍。
 * 在这条路线中，必须包含第 i 个障碍。
 * 你必须按障碍在 obstacles 中的 出现顺序 布置这些障碍。
 * 除第一个障碍外，路线中每个障碍的高度都必须和前一个障碍 相同 或者 更高 。
 * 返回长度为 n 的答案数组 ans ，其中 ans[i] 是上面所述的下标 i 对应的最长障碍赛跑路线的长度。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-longest-valid-obstacle-course-at-each-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1964_W {

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        // 求出的是数组 obstacles 中以每一个下标为结束位置的「最长递增子序列」的长度
        int n = obstacles.length;
        ArrayList<Integer> list = new ArrayList<>(n);
        List<Integer> ans = new ArrayList<>(n);
        for (int ob : obstacles) {
            if (list.isEmpty() || ob >= list.get(list.size() - 1)) {
                list.add(ob);
                ans.add(list.size());
            } else {
                int pos = rightBound(list, ob);
                ans.add(pos + 1);
                list.set(pos, ob);
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private int rightBound(List<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (target == list.get(mid)) {
                l = mid + 1;
            } else if (target > list.get(mid)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    // https://leetcode-cn.com/problems/find-the-longest-valid-obstacle-course-at-each-position/solution/5841-zhao-chu-dao-mei-ge-wei-zhi-wei-zhi-kdmq/
    int[] binaryTree;
    int n;
    public int[] test(int[] obstacles) {
        // 使用系统提供的排序函数在数组包含重复元素且是大到小的顺序排列时，离散化的结果不符合预期，如{2,2,1}的情况
        SortedSet<Integer> set = new TreeSet<>();
        //利用TreeSet可以同时实现排序和去重
        for(int ob : obstacles){
            set.add(ob);
        }
        n = set.size();

        List<Integer> diff = new ArrayList<>(set);// 包含重复元素，并且相同元素离散化后也要相同
        binaryTree = new int[n + 1];
        int[] ans = new int[obstacles.length];
        int i = 0;
        // 遍历obstacles的每一个数i, 我们需要求小于等于i的所有数中, 最长非递减序列长度的最大值, +1即为i对应的ans
        for (int ob : obstacles) {
            int idx = rightBound(diff, ob);
            int res = query(idx);
            ans[i++] = res + 1;
            update(idx, res + 1); // 注意理解这里update的操作
        }
        return ans;
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    private void update(int idx, int val) {
        while (idx <= n) {
            binaryTree[idx] = Math.max(val, binaryTree[idx]);
            idx += lowbit(idx);
        }
    }

    private int query(int idx) {
        int ans = 0;
        while (idx > 0) {
            ans = Math.max(binaryTree[idx], ans);
            idx -= lowbit(idx);
        }
        return ans;
    }
}
