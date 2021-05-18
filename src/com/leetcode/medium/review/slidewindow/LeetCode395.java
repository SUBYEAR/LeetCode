package com.leetcode.medium.review.slidewindow;

/**
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 *
 * @since 2021-02-27
 */
public class LeetCode395 {
	public int longestSubstring(String s, int k) {
		int res = 0;
		int len = s.length();
		for (int t = 1; t <= 26; t++) {
			int less = 0;
			int total = 0;
			int[] freq = new int[26];
			int left = 0, right = 0;
			while (right < len) {
				freq[s.charAt(right) - 'a']++;
				if (freq[s.charAt(right) - 'a'] == 1) {
					less++;
					total++;
				}

				if (freq[s.charAt(right) - 'a'] == k) {
					less--;
				}
				while (total > t) {
					freq[s.charAt(left) - 'a']--;
					if (freq[s.charAt(left) - 'a'] == 0) {
						total--;
						less--;
					}

					if (freq[s.charAt(left) - 'a'] == k - 1) {
						less++;
					}
					left++;
				}

				if (less == 0) {
					res = Math.max(res, right - left + 1);
				}
				right++;
			}
		}
		return res;
	}
}
