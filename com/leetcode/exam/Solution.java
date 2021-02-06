package com.leetcode.exam;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    private static  boolean isPalindrome(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++){
            if (s.charAt(i) != s.charAt(len- 1- i)) {
                return false;
            }
        }
        return true;
    }

    public static  String longestPalindrome(String s) {

        int lenMax = 0;
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i ; j < s.length();j++) {
                String test = s.substring(i, j);
                if (isPalindrome(test) && test.length() > lenMax) {
                    res = s.substring(i, j);
                    lenMax = Math.max(lenMax, test.length());
                }
            }
        }
        return res;
    }

    class Meeting {
        Integer start;
        Integer end;

        public Meeting(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int hashCode() {
            return start + end;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;

            if (getClass() != obj.getClass()) return false;
            Meeting tmp = (Meeting)obj;
            if ( tmp.start == this.start && tmp.end == this.end) {
                return  true;
            }
            return false;
        }
    }

    public int maxEvents(int[][] events) {
        Set<Meeting> meetSet= new HashSet<Meeting>();
        for (int[] a : events) {
            meetSet.add(new Meeting(a[0], a[1]));
        }
        return Math.min(events.length, meetSet.size());

    }

    public void subArray(int[] arr) {
        int len = arr.length;
        for (int i = len; i > 0; i--) {
            for (int j = 0; j + i < len; j++) {
                int[] ints = Arrays.copyOfRange(arr, j, j + len - 1);
                System.out.println(ints);
            }
        }
    }

}
