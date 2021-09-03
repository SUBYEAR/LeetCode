package com.leetcode.medium.review.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，
 * k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 * <p></p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

// 当每个人的身高不同时如果我们在初始时建立一个包含 n 个位置的空队列，而我们每次将一个人放入队列中时，会将一个「空」位置变成「满」位置，
// 那么当我们放入第 i 个人时，我们需要给他安排一个「空」位置，并且这个「空」位置前面恰好还有 k_i 个「空」位置，
// 用来安排给后面身高更高的人。也就是说，第 i个人的位置，就是队列中从左往右数第 k_i+1 个「空」位置。
// 当身高相等时k_i > k_j 那么说明 i 一定相对于 j 在队列中处于较后的位置
public class LeetCode406_W {
    public int[][] reconstructQueue(int[][] people) {
        // 需要按照 hi 为第一关键字降序，ki为第二关键字升序进行排序即可。
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            ans.add(person[1], person); // 第 i个人的位置，就是队列中从左往右数第 k_i+1 个「空」位置
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
