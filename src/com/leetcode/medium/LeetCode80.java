package com.leetcode.medium;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 *  
 *
 * 说明：
 *
 * 为什么返回数值是整数，但输出的答案是数组呢？
 *
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode80 {
    public int removeDuplicates(int[] nums) {
        int left = 0;
        int right = 0;
        int len = nums.length;
        while (right < len) {
            if (nums[right] == nums[left]) {
                right++;
            } else {
                if (right - left > 2) {
                    swap(nums, left, len - 1);
                    right--;
                    len--;
                } else {
                    left = right;
                }
            }
        }
        return right - left > 2 ? left + 1 : len;
    }

    void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        for (int i = start + 1; i <= end; i++) {
            nums[i - 1] = nums[i];
        }
        nums[end] = temp;
    }

    public int removeDuplicates2(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    // 删除重复元素只保留一次的解法
    // 数组完成排序后，我们可以放置两个指针 i 和 j，其中 i 是慢指针，而 j 是快指针。只要 nums[i] == nums[j]，
    // 我们就增加 j 以跳过重复项。当我们遇到 nums[i] != nums[j] 时，跳过重复项的运行已经结束，
    // 因此我们必须把它（nums[j]）的值复制到 nums[i+1]。然后递增 i，
    // 接着我们将再次重复相同的过程，直到 j 到达数组的末尾为止
    public int removeDuplicate(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}

// 官方解法
//     public int removeDuplicates(int[] nums) {
//        int n = nums.length;
//        if (n <= 2) {
//            return n;
//        }
//        int slow = 2, fast = 2;
//        while (fast < n) {
//            if (nums[slow - 2] != nums[fast]) {
//                nums[slow] = nums[fast];
//                ++slow;
//            }
//            ++fast;
//        }
//        return slow;
//    }
