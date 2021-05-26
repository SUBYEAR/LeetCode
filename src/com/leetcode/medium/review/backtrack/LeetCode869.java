package com.leetcode.medium.review.backtrack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 *
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 *
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
}
