package com.leetcode.hard;

import java.util.*;

/**
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 *
 * 注意: 允许出现重复元素。
 *
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode381_W {
    Map<Integer, Set<Integer>> idx;
    List<Integer> nums; // 将每个数值（可以重复）存储在一个列表 nums 中

    /** Initialize your data structure here. */
    public LeetCode381_W() {
        idx = new HashMap<Integer, Set<Integer>>();
        nums = new ArrayList<Integer>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        nums.add(val);
        Set<Integer> set = idx.getOrDefault(val, new HashSet<Integer>());
        set.add(nums.size() - 1);
        idx.put(val, set);
        return set.size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        // 列表中元素的顺序是无关紧要的，只要它们正确地存在于列表中即可。
        // 因此，在删除元素时，我们可以将被删的元素与列表中最后一个元素交换位置，随后便可以在 O(1) 时间内，从列表中去除该元素
        if (!idx.containsKey(val)) {
            return false;
        }
        Iterator<Integer> it = idx.get(val).iterator();
        int i = it.next(); // 要删除的值在nums列表中的最后出现的索引
        int lastNum = nums.get(nums.size() - 1);

        nums.set(i, lastNum); // 最后一个数和val删除的索引进行交换
        idx.get(val).remove(i);
        idx.get(lastNum).remove(nums.size() - 1);
        if (i < nums.size() - 1) { // 这里的判断很重要，考虑nums和idx中均存在一个元素，且这个元素相同，i肯定是小于等于size - 1的
            // 要排查的情况是i == size - 1。 如果没有判断条件那么这里数据存储和实际情况就会对应不上
            idx.get(lastNum).add(i);
        }
        if (idx.get(val).size() == 0) {
            idx.remove(val);
        }
        nums.remove(nums.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() { // 随机选择nums中的下标进行访问可以满足每个元素被返回的概率应该与其在集合中的数量呈线性相关
        return nums.get((int) (Math.random() * nums.size())); // 使用LeetCode528的方式来随机会超时
    }
}
