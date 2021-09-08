package com.leetcode.medium.review.greedy;

import java.util.Arrays;

/**
 * 如果某个整数是 num 中各位数字的一个 排列 且它的 值大于 num ，则称这个整数为 妙数 。可能存在很多妙数，但是只需要关注 值最小 的那些。
 *
 * 例如，num = "5489355142" ：
 * 第 1 个最小妙数是 "5489355214"
 * 第 2 个最小妙数是 "5489355241"
 * 第 3 个最小妙数是 "5489355412"
 * 第 4 个最小妙数是 "5489355421"
 * 返回要得到第 k 个 最小妙数 需要对 num 执行的 相邻位数字交换的最小次数 。
 *
 * 测试用例是按存在第 k 个最小妙数而生成的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1850_W {
    public int getMinSwaps(String num, int k) {
        int n = num.length();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = num.charAt(i) - '0';
        }
        int[] origin = Arrays.copyOf(nums, n);
        for (int i = 0; i < k; i++) {
            nextPermutation(nums);
        }
        // 使用贪心的方法得到最少的交换次数。具体地，我们对 num 和 num_K同时进行遍历,设当前遍历位置是i
        // 如果 num[i]=num_k[i]那么无需进行任何交换
        // 如果num[i]!=num_k[i],那么需要找出一个出现在num[i]之后的字符num[j]使得num[j]=num_k[i],然后num[j]经过j-1次操作交换到i位置
        // 如果满足要求的j位置有多个选择j最小的那个
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (origin[i] != nums[i]) {
                for (int j = i + 1; j < n; j++) {
                    if (origin[j] == nums[i]) {
                        for (int idx = j - 1; idx >= i; idx--) {
                            ++ans;
                            swap(origin, idx, idx + 1);
                        }
                        break; // 注意这里的break别忘了
                    }
                }
            }
        }

        return ans;
    }

    void nextPermutation(int[] nums) {
        int len = nums.length;
        int i = len - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i < 0) {
            return;
        }

        int j = len - 1;
        while (j >= 0 && nums[j] <= nums[i]) {
            j--;
        }
        swap(nums, i, j);
        reverse(nums, i + 1);
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
