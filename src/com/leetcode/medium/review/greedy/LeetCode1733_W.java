package com.leetcode.medium.review.greedy;

import java.util.*;

/**
 * 在一个由 m 个用户组成的社交网络里，我们获取到一些用户之间的好友关系。两个用户之间可以相互沟通的条件是他们都掌握同一门语言。
 *
 * 给你一个整数 n ，数组 languages 和数组 friendships ，它们的含义如下：
 *
 * 总共有 n 种语言，编号从 1 到 n 。
 * languages[i] 是第 i 位用户掌握的语言集合。
 * friendships[i] = [u​​​​​​i​​​, v​​​​​​i] 表示 u​​​​​​​​​​​i​​​​​ 和 vi 为好友关系。
 * 你可以选择 一门 语言并教会一些用户，使得所有好友之间都可以相互沟通。请返回你 最少 需要教会多少名用户。
 *
 * 请注意，好友关系没有传递性，也就是说如果 x 和 y 是好友，且 y 和 z 是好友， x 和 z 不一定是好友。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-people-to-teach
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1733_W {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Set<Integer> set = new HashSet<>(); // 集合存放好友关系中没有共同语言的人
        Map<Integer, Integer> mostLanguage = new HashMap<>(); // 没有共同语言的好友中使用语言的频数表
        for (int[] friend : friendships) {
            if (!isConnected(languages[friend[0] - 1], languages[friend[1] - 1])) {
                set.add(friend[0]);
                set.add(friend[1]);
            }
        }

        for (int user : set) {
            for (int language : languages[user - 1]) {
                mostLanguage.put(language, mostLanguage.getOrDefault(language, 0) + 1);
            }
        }

        // 没有共同语言的人中使用的最多的一种语言的数目
        int most = mostLanguage.values().stream().max(Comparator.naturalOrder()).orElse(0);
        // 需要再学习该语言的人数 即他们的总人数-会该语言的人数
        return set.size() - most;
    }


    boolean isConnected(int[] user1, int[] user2) { // 用户之间是否有共同语言
        Arrays.sort(user1);
        Arrays.sort(user2);
        int i = 0;
        int j = 0;
        while (i < user1.length && j < user2.length) {
            if (user1[i] == user2[j]) {
                return true;
            }
            if (user1[i] < user2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return false;
    }
}
