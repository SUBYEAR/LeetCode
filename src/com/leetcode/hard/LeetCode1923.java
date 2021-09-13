package com.leetcode.hard;

import java.util.HashSet;

/**
 * 一个国家由 n 个编号为 0 到 n - 1 的城市组成。在这个国家里，每两个 城市之间都有一条道路连接。
 *
 * 总共有 m 个编号为 0 到 m - 1 的朋友想在这个国家旅游。他们每一个人的路径都会包含一些城市。
 * 每条路径都由一个整数数组表示，每个整数数组表示一个朋友按顺序访问过的城市序列。同一个城市在一条路径中可能 重复 出现，但同一个城市在一条路径中不会连续出现。
 *
 * 给你一个整数 n 和二维数组 paths ，其中 paths[i] 是一个整数数组，
 * 表示第 i 个朋友走过的路径，请你返回 每一个 朋友都走过的 最长公共子路径 的长度，如果不存在公共子路径，请你返回 0 。
 *
 * 一个 子路径 指的是一条路径中连续的城市序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-subpath
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1923 { // 后缀数字可以了解
    // 滚动哈希将一个字符串看成某个进制下的整数，并将其对应的十进制值作为哈希值.
    // 在子数组长度固定的前提下，给定进制 k，子数组与其十进制值满足「一一对应」的关系，
    // 即不会有两个不同的子数组，它们的十进制值相同。因此滚动哈希得到的哈希值是可以表示原子数组的。
    // 滚动哈希的一大优势在于，如果我们需要求出一个数组中长度为 len 的所有子数组的哈希值，需要的时间仅为线性，
    // 即如果我们已经计算出数组中以 j 开始的子数组的哈希值：那么要想计算以 j+1 开始的子数组的哈希值，我们通过hash[j]递推得到
    // 问题的关键是要得到一个没有碰撞的哈希函数
    HashSet<Long> allhash;
    int[][] paths;
    int o ;
    long hash;

    public int longestCommonSubpath(int n, int[][] paths) {
        if (paths.length == 1)
            return paths[0].length;
        int right = paths[0].length;
        this.paths = paths;
        for (int[] c : paths) {
            right = Math.min(right, c.length);
        }

        int left = 1;
        int res = 0;
        for (int i = 1; right >= left; ) { // 二分查找
            i = (right + left) >> 1;
            if (CommonSubpath(i)) {
                left = i + 1;
                res = Math.max(res, i);
            } else {
                right = i - 1;
            }
            allhash = null;
        }
        return res;
    }

    // 为哈希冲突加入一个必须的前置条件，即如果哈希冲突则一定要最后一个下标值相等。
    // 在设计哈希值时除了下标值加上一个质数再乘以该段的下标外，
    // 再加入该段最后一个下标值的变化即可
    public boolean CommonSubpath(int m) { // 滚动哈希
        int temp = 0;
        int left = 0;
        allhash = new HashSet<Long>();
        hash = 0;
        for (o = 0; o < m; o++) {
            hash += (long) (paths[0][o] + 13) * (o + 1);
            temp += paths[0][o];
        }
        allhash.add(hash * (paths[0][o - 1] + 3) + paths[0][o - 1] % 31);
        while (o < paths[0].length) {
            hash = hash - temp + (long) paths[0][o] * m;
            temp -= paths[0][left++];
            temp += paths[0][o];
            allhash.add(hash * (paths[0][o] + 3) + paths[0][o] % 31);
            o++;
        }
        for (int i = 1; i < paths.length; i++) {
            left = 0;
            temp = 0;
            HashSet<Long> allhash1 = new HashSet<Long>();
            hash = 0;
            for (o = 0; o < m; o++) {
                hash += (long) (paths[i][o] + 13) * (o + 1);
                temp += paths[i][o];
            }
            long a = hash * (paths[i][o - 1] + 3) + paths[i][o - 1] % 31;
            if (allhash.contains(a))
                allhash1.add(a);
            while (o < paths[i].length) {
                hash = hash - temp + (long) paths[i][o] * m;
                temp -= paths[i][left++];
                temp += paths[i][o];
                a = hash * (paths[i][o] + 3) + paths[i][o] % 31;
                if (allhash.contains(a))
                    allhash1.add(a);
                o++;
            }
            if (allhash1.size() == 0)
                return false;
            allhash = allhash1;
        }
        return true;
    }
//    链接：https://leetcode-cn.com/problems/longest-common-subpath/solution/java-xin-si-lu-115ms-by-zhu-sir-7-hk5m/
}
