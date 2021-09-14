package com.leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 有 n 个人，每个人都有一个  0 到 n-1 的唯一 id 。
 *
 * 给你数组 watchedVideos  和 friends ，其中 watchedVideos[i]  和 friends[i] 分别表示 id = i 的人观看过的视频列表和他的好友列表。
 *
 * Level 1 的视频包含所有你好友观看过的视频，level 2 的视频包含所有你好友的好友观看过的视频，以此类推。一般的，Level 为 k 的视频包含所有从你出发，最短距离为 k 的好友观看过的视频。
 *
 * 给定你的 id  和一个 level 值，请你找出所有指定 level 的视频，并将它们按观看频率升序返回。如果有频率相同的视频，请将它们按字母顺序从小到大排列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/get-watched-videos-by-your-friends
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1311 {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        Queue<Integer> que = new ArrayDeque<>();
        que.add(id);
        boolean[] seen = new boolean[n];
        seen[id] = true;
        while (!que.isEmpty() && level > 0) {
            int size = que.size();
            while (size-- > 0) {
                int cur = que.poll();
                for (int next : friends[cur]) {
                    if (seen[next]) {
                        continue;
                    }
                    seen[next] = true;
                    que.add(next);
                }
            }
            --level;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int people : que) {
            for (String video : watchedVideos.get(people)) {
                map.put(video, map.getOrDefault(video, 0) + 1);
            }
        }
        List<String> videos = new ArrayList<>(map.keySet());
        List<Integer> cnt = new ArrayList<>(map.values());
        List<Integer> sorted = IntStream.range(0, videos.size()).boxed().sorted((o1, o2) -> {
            if (cnt.get(o1).equals(cnt.get(o2))) {
                return videos.get(o1).compareTo(videos.get(o2));
            }
            return cnt.get(o1).compareTo(cnt.get(o2));
        }).collect(Collectors.toList());

        List<String> ans = new ArrayList<>();
        for (Integer idx : sorted) {
            ans.add(videos.get(idx));
        }
        return ans;
    }
}
