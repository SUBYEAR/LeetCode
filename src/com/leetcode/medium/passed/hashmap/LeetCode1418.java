package com.leetcode.medium.passed.hashmap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，
 * 其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
 *
 * 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。
 * 接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
 *
 * 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/display-table-of-food-orders-in-a-restaurant
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1418 {
    public List<List<String>> displayTable(List<List<String>> orders) {
        TreeSet<String> allFoods = new TreeSet<>();
        for (List<String> order : orders) {
            String foodItem = order.get(2);
            allFoods.add(foodItem);
        }

        List<List<String >> res = new LinkedList<>();
        LinkedList<String> title = new LinkedList<>(allFoods);
        title.addFirst("Table");
        res.add(title);
        TreeMap<String, int[]> info = new TreeMap<>((o1, o2) -> Integer.parseInt(o1) - Integer.parseInt(o2));
        for (List<String> order : orders) {
            String tableNumber = order.get(1);
            String foodItem = order.get(2);

            int[] row = info.getOrDefault(tableNumber, new int[allFoods.size() + 1]);
            row[0] = Integer.parseInt(tableNumber);
            int index = title.indexOf(foodItem);
            ++row[index];
            info.put(tableNumber, row);
        }

        for (Map.Entry<String, int[]> e : info.entrySet()) {
            List<String> nums = Arrays.stream(e.getValue()).mapToObj(String::valueOf).collect(Collectors.toList());
            res.add(nums);

        }
        return res;
    }
}
