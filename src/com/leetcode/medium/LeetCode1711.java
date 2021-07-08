package com.leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
 *
 * 你可以搭配 任意 两道餐品做一顿大餐。
 *
 * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i
 * 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。
 *
 * 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-good-meals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1711 {
    public int countPairs(int[] deliciousness) {
        Set<Integer>[] feats = new HashSet[22];
        for (int i = 0; i < 22; i++) {
            feats[i] = new HashSet<>();
        }
        long res = 0;
        final int mod = 1000_000_007;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int de : deliciousness) {
            freq.put(de, freq.getOrDefault(de, 0) + 1);
        }

        for (int de : deliciousness) {
            for (int i = 21; i >= 0; i--) {
                int expect = 1 << i;
                if (expect - i < 0) {
                    break;
                }
                int cnt = freq.getOrDefault(expect - de, 0);
                if (cnt == 0 || feats[i].contains(de)) {
                    continue;
                }
                long add = 0;
                if (2 * de == expect) {
                    add = (((long) cnt * (cnt - 1)) / 2) % mod;
                } else {
                    add = (long) cnt * freq.get(de) % mod;
                }
                res = (res + add) % mod;
                feats[i].add(de);
                feats[i].add(expect - de);
            }
        }

        return (int) res;
    }
}
