package com.leetcode.medium.review;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
 * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。
 * (另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
 * 返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），
 * 代表学生坐的位置；函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。每次调用 ExamRoom.leave(p) 时都保证有学生坐在座位 p 上。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/exam-room
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode855_W {
    // 端点p映射到以p左端点的线段
    private Map<Integer, int[]> startMap;
    // 端点p映射到以p右端点的线段
    private Map<Integer, int[]> endMap;

    private TreeSet<int[]> pq; // 存放所有线段

    private int N;

    public LeetCode855_W(int n) {
        this.N = n;
        startMap = new HashMap<>();
        endMap = new HashMap<>();
        pq = new TreeSet<>((a, b) -> {
            int distanceA = getDistance(a);
            int distanceB = getDistance(b);
            if (distanceA == distanceB) {
                // 如果长度相同就比较索引
                return a[0] - b[0];
            }
            return distanceA - distanceB;
        });
        addInterval(new int[] {-1, N});
    }

    public int seat() {
        int[] longest = pq.last();
        int x = longest[0], y = longest[1];
        int seat;
        if (x == -1) {
            seat = 0;
        } else if (y == N) {
            seat = N - 1;
        } else {
            seat = (y - x) / 2 + x;
        }

        int[] left = new int[] {x, seat};
        int[] right = new int[] {seat, y};
        removeInterval(longest);
        addInterval(left);
        addInterval(right);
        return seat;
    }

    public void leave(int p) {
        int[] right = startMap.get(p);
        int[] left = endMap.get(p);

        int[] merged = new int[] {left[0], right[1]};
        removeInterval(left);
        removeInterval(right);
        addInterval(merged);
    }

    private void addInterval(int[] arr) {
        pq.add(arr);
        startMap.put(arr[0], arr);
        endMap.put(arr[1], arr);
    }

    private void removeInterval(int[] arr) {
        pq.remove(arr);
        startMap.remove(arr[0]);
        endMap.remove(arr[1]);
    }

    private int getDistance(int[] arr) {

        int x = arr[0], y = arr[1];
        return (y - x) / 2; // 中点和端点的长度
    }
}
