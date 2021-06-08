package com.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给你一个餐馆信息数组 restaurants，其中  restaurants[i] = [idi, ratingi, veganFriendlyi, pricei, distancei]。
 * 你必须使用以下三个过滤器来过滤这些餐馆信息。
 *
 * 其中素食者友好过滤器 veganFriendly 的值可以为 true 或者 false，如果为 true 就意味着你应该只包括 veganFriendlyi 为
 * true 的餐馆，为 false 则意味着可以包括任何餐馆。此外，我们还有最大价格 maxPrice 和最大距离 maxDistance 两个
 * 过滤器，它们分别考虑餐厅的价格因素和距离因素的最大值。
 *
 * 过滤后返回餐馆的 id，按照 rating 从高到低排序。如果 rating 相同，那么按 id 从高到低排序。简单起见， veganFriendlyi 和
 * veganFriendly 为 true 时取值为 1，为 false 时，取值为 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/filter-restaurants-by-vegan-friendly-price-and-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1333 {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        int[][] filter = Arrays.stream(restaurants)
                .filter(r -> r[3] <= maxPrice && r[4] <= maxDistance)
                .toArray(int[][]::new);
        if (veganFriendly == 1) {
            filter = Arrays.stream(filter).filter(r -> r[2] == 1).toArray(int[][]::new);
        }

        Arrays.sort(filter, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o2[0] - o1[0];
            }
            return o2[1] - o1[1];
        });

        List<Integer> res = new LinkedList<>();
        for (int[] r : filter) {
            res.add(r[0]);
        }
        return res;
    }
}
