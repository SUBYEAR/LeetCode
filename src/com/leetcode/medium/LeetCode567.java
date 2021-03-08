package com.leetcode.medium;

public class LeetCode567 {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] s1Map = new int[26];
        int[] s2Map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            ++s1Map[s1.charAt(i) - 'a'];
            ++s2Map[s2.charAt(i) - 'a'];
        }

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (isMatch(s1Map, s2Map)) {
                return true;
            }
            --s2Map[s2.charAt(i) - 'a'];
            ++s2Map[s2.charAt(i + s1.length()) - 'a'];
        }
        return isMatch(s1Map, s2Map);
    }

    boolean isMatch(int[] s1Map, int[] s2Map) {
        for (int i = 0; i < s1Map.length; i++) {
            if (s1Map[i] != s2Map[i]) {
                return false;
            }
        }
        return true;
    }
}

// 双指针解法 还可以在保证 cnt 的值不为正的情况下，去考察是否存在一个区间，其长度恰好为 n
//  public boolean checkInclusion(String s1, String s2) {
//        int n = s1.length(), m = s2.length();
//        if (n > m) {
//            return false;
//        }
//        int[] cnt = new int[26];
//        for (int i = 0; i < n; ++i) {
//            --cnt[s1.charAt(i) - 'a'];
//        }
//        int left = 0;
//        for (int right = 0; right < m; ++right) {
//            int x = s2.charAt(right) - 'a';
//            ++cnt[x];
//            while (cnt[x] > 0) {
//                --cnt[s2.charAt(left) - 'a'];
//                ++left;
//            }
//            if (right - left + 1 == n) {
//                return true;
//            }
//        }
//        return false;
//    }
