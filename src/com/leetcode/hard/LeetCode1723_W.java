package com.leetcode.hard;

import java.util.Arrays;

/**
 * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
 * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的工作时间 是完成分配给他们的所有工作花费时间的总和
 * 。请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
 * 返回分配方案中尽可能 最小 的 最大工作时间 。
 * <p></p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1723_W {
    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        int low = 0, high = jobs.length -1;
        while (low < high) { // jobs降序排列
            int temp = jobs[low];
            jobs[low] = jobs[high];
            jobs[high] = temp;
            low++;
            high--;
        }

        int l = 0, r = Arrays.stream(jobs).sum();
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (check(jobs, k, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    boolean check(int[] jobs, int k, int limit) {
        int[] workloads = new int[k];
        return backtrack(jobs, workloads, 0, limit);
    }

    boolean backtrack(int[] jobs, int[] workloads, int i, int limit) {
        if (i >= jobs.length) {
            return true;
        }
        int cur = jobs[i];
        for (int j = 0; j < workloads.length; j++) {
            if (workloads[j] + cur <= limit) {
                workloads[j] += cur;
                if (backtrack(jobs, workloads, i + 1, limit)) {
                    return true;
                }
                workloads[j] -= cur;
            }
            // 如果当前工人未被分配工作，那么下一个工人也必然未被分配工作
            // 或者当前工作恰能使该工人的工作量达到了上限
            // 这两种情况下我们无需尝试继续分配工作
            if (workloads[j] == 0 || workloads[j] + cur == limit) { // 不要第二个条件也可以执行成功
                // 就是 jobs[i]是从大到下排序的，若jobs[i]给工人j后，工人j的工作量刚好等于limit，此时剩余的工作分配失败（即返回false），
                // 说明jobs[i]之后的小工作量不能被剩余的工人在limit期限内完成；若此时将jobs[i]之后的小工作量（可能是多个，没影响）给到工人j，
                // 也就是job[i]分给工人j之后的工人，相当于分给工人j之后工人的工作量增大了，自然更无法完成，所以剩余的情况不需要枚举了，直接break
                break;
            }
        }
        return false;
    }
}
