package com.leetcode.medium.review.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个仅由数字组成的字符串 s 。
 *
 * 请你判断能否将 s 拆分成两个或者多个 非空子字符串 ，使子字符串的 数值 按 降序 排列，且每两个 相邻子字符串 的数值之 差 等于 1 。
 *
 * 例如，字符串 s = "0090089" 可以拆分成 ["0090", "089"] ，数值为 [90,89] 。这些数值满足按降序排列，且相邻值相差 1 ，这种拆分方法可行。
 * 另一个例子中，字符串 s = "001" 可以拆分成 ["0", "01"]、["00", "1"] 或 ["0", "0", "1"] 。然而，所有这些拆分方法都不可行，
 * 因为对应数值分别是 [0,1]、[0,1] 和 [0,0,1] ，都不满足按降序排列的要求。
 * 如果可以按要求拆分 s ，返回 true ；否则，返回 false 。
 *
 * 子字符串 是字符串中的一个连续字符序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "1234"
 * 输出：false
 * 解释：不存在拆分 s 的可行方法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/splitting-a-string-into-descending-consecutive-values
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1849 {
    boolean res = false;
    public boolean splitString(String s) {
        dfs(s.toCharArray(), 0, new ArrayList<>());
        return res;
    }

    void dfs(char[] arr, int start, ArrayList<Long> path) {
        if (start == arr.length) {
            System.out.println(path);
            if (isDescent(path)) {
                res = true;
            }
            return;
        }

        long value = 0;
        for (int i = start; i < arr.length; i++) {
            value = value * 10 + arr[i] - '0';
            if (!path.isEmpty() && value >= path.get(path.size() - 1)) {
                break;
            }
            path.add(value);
            dfs(arr, i + 1, path);
            path.remove(path.size() - 1);
        }
    }

    boolean isDescent(ArrayList<Long> path) {
        for (int i = 1; i < path.size(); i++) {
            if (!(path.get(i - 1) > path.get(i) && (path.get(i - 1) == path.get(i) + 1))) {
                return false;
            }
        }
        return path.size() > 1;
    }
}
