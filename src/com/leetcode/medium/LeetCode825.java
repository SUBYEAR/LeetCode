package com.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 人们会互相发送好友请求，现在给定一个包含有他们年龄的数组，ages[i] 表示第 i 个人的年龄。
 *
 * 当满足以下任一条件时，A 不能给 B（A、B不为同一人）发送好友请求：
 *
 * age[B] <= 0.5 * age[A] + 7
 * age[B] > age[A]
 * age[B] > 100 && age[A] < 100
 * 否则，A 可以给 B 发送好友请求。
 *
 * 注意如果 A 向 B 发出了请求，不等于 B 也一定会向 A 发出请求。而且，人们不会给自己发送好友请求。 
 *
 * 求总共会发出多少份好友请求?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/friends-of-appropriate-ages
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode825 {
    public int numFriendRequests(int[] ages) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int age : ages) {
            if (age <= 14) continue;
            freq.put(age, freq.getOrDefault(age, 0) + 1);
        }

        int[] uniqueAges = freq.keySet().stream().sorted().mapToInt(Integer::intValue).toArray();
        int res = 0;
        int right = uniqueAges.length - 1;

        while (right >= 0) {
            int leftBound = uniqueAges[right] / 2 + 7;
            int cnt = freq.get(uniqueAges[right]);
            res += (cnt * (cnt - 1));

            int left = right - 1;
            while (left >= 0 && uniqueAges[left] > leftBound) {
                res += cnt * freq.get(uniqueAges[left]);
                left--;
            }
            right--;
        }

        return res;
    }

    // 官解
    //  public int numFriendRequests(int[] ages) {
    //        int[] count = new int[121];
    //        for (int age: ages) count[age]++;
    //
    //        int ans = 0;
    //        for (int ageA = 0; ageA <= 120; ageA++) {
    //            int countA = count[ageA];
    //            for (int ageB = 0; ageB <= 120; ageB++) {
    //                int countB = count[ageB];
    //                if (ageA * 0.5 + 7 >= ageB) continue;
    //                if (ageA < ageB) continue;
    //                if (ageA < 100 && 100 < ageB) continue;
    //                ans += countA * countB;
    //                if (ageA == ageB) ans -= countA;
    //            }
    //        }
    //
    //        return ans;
    //    }
}
