package com.leetcode.medium.review;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 *
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 *
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/online-stock-span
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 2021-02-19
 */
public class LeetCode901 {
    class StockSpanner {
        Deque<Integer> prices;
        Deque<Integer> weight;
        public StockSpanner() {
            prices = new LinkedList<>();
            weight = new LinkedList<>();
        }

        public int next(int price) {
            int cross = 1;
            while (!prices.isEmpty() && price >= prices.peek()) {
                prices.pop();
                cross += weight.pop();
            }
            prices.push(price);
            weight.push(cross);
            return cross;
        }
    }
}
