package com.leetcode.exam;

import java.util.HashMap;

public class Test0423 {
    // #Title3
    public String getMaxSpecialStr(String input) {
        HashMap<Character, Integer> times = new HashMap<>();
        int length = input.length();
        char[] arr = input.toCharArray();
        int begin = 0;
        int end = 0;
        int maxi = -1;
        int maxj = -1;
        int max = 0;
        int count = 0;
        while (end < length) {
            times.put(arr[end], times.getOrDefault(arr[end], 0) + 1);
            if (times.get(arr[end]) == 1) {
                count++;
            }
            if (times.get(arr[end]) == 2) {
                count--;
            }
            while (begin > 0 && count <= 2) {
                begin--;
                times.put(arr[begin], times.get(arr[begin]) + 1);
                if (times.get(arr[begin]) == 1) {
                    count++;
                }
                if (times.get(arr[begin]) == 2) {
                    count--;
                }
            }
            while (count > 2) {
                if (times.get(arr[begin]) == 1) {
                    count--;
                }
                if (times.get(arr[begin]) == 2) {
                    count++;
                }
                times.put(arr[begin], times.get(arr[begin]) - 1);
                begin++;
            }
            if (count == 2 && end - begin + 1 > max) {
                max = end - begin + 1;
                maxi = begin;
                maxj = end;
            }
            end++;
        }
        return input.substring(maxi, maxj);
    }
}
