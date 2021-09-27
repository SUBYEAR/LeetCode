package com.leetcode.medium.review.hashmap;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 */
public class LeetCode981 {
    Map<String, String> map;
    TreeSet<Integer> timeMap;
    public LeetCode981() {
        map = new HashMap<>();
        timeMap = new TreeSet<>();
    }

    public void set(String key, String value, int timestamp) {
        map.put(key + timestamp, value);
        timeMap.add(timestamp);
    }

    public String get(String key, int timestamp) {
        Integer floorKey = timeMap.floor(timestamp);
        if (floorKey == null) {
            return "";
        }
        for (int i = floorKey; i > 0; i--) {
            String s = key + i;
            if (map.containsKey(s)) {
                return map.get(s);
            }
        }
        return "";
    }

    // 官方解法
    // class Pair implements Comparable<Pair> {
    //        int timestamp;
    //        String value;
    //
    //        public Pair(int timestamp, String value) {
    //            this.timestamp = timestamp;
    //            this.value = value;
    //        }
    //
    //        public int hashCode() {
    //            return timestamp + value.hashCode();
    //        }
    //
    //        public boolean equals(Object obj) {
    //            if (obj instanceof Pair) {
    //                Pair pair2 = (Pair) obj;
    //                return this.timestamp == pair2.timestamp && this.value.equals(pair2.value);
    //            }
    //            return false;
    //        }
    //
    //        public int compareTo(Pair pair2) {
    //            if (this.timestamp != pair2.timestamp) {
    //                return this.timestamp - pair2.timestamp;
    //            } else {
    //                return this.value.compareTo(pair2.value);
    //            }
    //        }
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/time-based-key-value-store/solution/ji-yu-shi-jian-de-jian-zhi-cun-chu-by-le-t98o/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
