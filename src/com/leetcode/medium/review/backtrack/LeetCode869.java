package com.leetcode.medium.review.backtrack;

import java.util.Arrays;

/**
 * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 *
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 * 提示：
 *
 * 1 <= N <= 10^9
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reordered-power-of-2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode869 {
    public boolean reorderedPowerOf2(int n) {
        return backtrack(String.valueOf(n).toCharArray(), 0);
    }

    boolean backtrack(char[] arr, int pos) {
        if (pos == arr.length) {
            if (arr[0] == '0') {
                return false;
            }
            int num = Integer.parseInt(new String(arr));
            if (Integer.bitCount(num) == 1) {
                return true;
            }
            return false;
        }

        for (int i = pos; i < arr.length; i++) {
            if (isExchangeable(arr, pos, i)) {
                swap(arr, i, pos);
                if (backtrack(arr, pos + 1)) {
                    return true;
                }
                swap(arr, i, pos);
            }
        }
        return false;
    }

    void swap(char arr[], int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    boolean isExchangeable(char[] arr, int begin, int end) {
        for (int i = begin; i < end; i++) {
            if (arr[i] == arr[end]) {
                return false;
            }
        }

        return true;
    }

    // 计数法 N 只能是 2 的幂，我们只需要检查 N 跟这些幂是不是拥有一样数字构成
    public boolean reorderedPowerOf2_Count(int N) {
        int[] A = count(N);
        for (int i = 0; i < 31; ++i)
            if (Arrays.equals(A, count(1 << i)))
                return true;
        return false;
    }

    // Returns the count of digits of N
    // Eg. N = 112223334, returns [0,2,3,3,1,0,0,0,0,0]
    public int[] count(int N) {
        int[] ans = new int[10];
        while (N > 0) {
            ans[N % 10]++;
            N /= 10;
        }
        return ans;
    }
}
