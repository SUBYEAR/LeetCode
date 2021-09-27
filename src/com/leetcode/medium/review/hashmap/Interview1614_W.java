package com.leetcode.medium.review.hashmap;

import java.util.HashMap;

/**
 * 给定一个二维平面及平面上的 N 个点列表Points，其中第i个点的坐标为Points[i]=[Xi,Yi]。请找出一条直线，其通过的点的数目最多。
 * 设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为S，你仅需返回[S[0],S[1]]作为答案，若有多条直线穿过了相同数量的点，
 * 则选择S[0]值较小的直线返回，S[0]相同则选择S[1]值较小的直线返回。
 *
 * 示例：
 *
 * 输入： [[0,0],[1,1],[1,0],[2,0]]
 * 输出： [0,2]
 * 解释： 所求直线穿过的3个点的编号为[0,2,3]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-line-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Interview1614_W {
    public int[] bestLine(int[][] points) {
        int max = 0;
        int maxHash = 0;
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, int[]> record = new HashMap<>();
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int A = points[j][1] - points[i][1];
                int B = points[i][0] - points[j][0];
                int C = points[j][0] * points[i][1] - points[i][0] * points[j][1];
                int gcd = getGcd(getGcd(A, B), C);
                A /= gcd;
                B /= gcd;
                C /= gcd;
                int hash = hash(A, B, C);
                int count = map.getOrDefault(hash, 0) + 1;
                map.put(hash, count);
                if (count == 1) {
                    record.put(hash, new int[]{i, j});
                }
                if (count > max) {
                    max = count;
                    maxHash = hash;
                    res = record.get(hash);
                } else if (count == max) {
                    int[] t1 = record.get(maxHash);
                    int[] t2 = record.get(hash);
                    if (t1[0] > t2[0] || t1[0] == t2[0] && t1[1] > t2[1]) {
                        maxHash = hash;
                        res = t2;
                    }
                }
            }
        }
        return res;
    }

    public int getGcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private int hash(int a,int b,int c){
        a = (a ^ (a >>> 16) & 0x0000ffff) << 20;
        b = (b ^ (b >>> 16) & 0x0000ffff) << 10;
        c = c ^ (c >>> 16) & 0x00000ffff;
        return a | b | c;
    }

    // 链接：https://leetcode-cn.com/problems/best-line-lcci/solution/shu-xue-ti-zhi-xian-de-yi-ban-shi-fang-cheng-by-tu/

}
