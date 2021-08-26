package com.leetcode.exam;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Test0326 {
    // #Title_2 字符编码无歧义:在字符串中插入校验码每个校验码后跟对应数字的个数,校验码大于0且无前导0
    // 如109something就是有歧义的, 3000无歧义返回3
    int flag = 0;
    int allLength = 0;
    public int getLength(String encodingString) {
        if (encodingString.length() < 2) {
            return -1;
        }

        int len = 0;
        for (int i = 0; i < encodingString.length(); i++) {
            if (encodingString.charAt(i) >= 'a' && encodingString.charAt(i) <= 'z') {
                break;
            }
            len = len * 10 + encodingString.charAt(i) - '0';
            if (i + len + 1 <= encodingString.length()) {
                getSubLength(encodingString, i + len + 1, len);
            }
        }

        return flag == 1 ? allLength : -1;
    }

    void getSubLength(String s, int start, int count) {
        if (start == s.length()) {
            flag++;
            allLength += count;
            return;
        }

        if (s.charAt(start) == '0') {
            return;
        }

        int length = 0;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                break;
            }
            length = length * 10 + s.charAt(i) - '0';
            if (i + length + 1 <= s.length()) {
                getSubLength(s, i + length + 1, count + length);
            }
        }
    }

    // #Title_3
    public int[][] experimentBox(int rowCount, int colCount, int[] obstacle, int[][] irons, String directions) {
        // 第j列的障碍物上边沿，给上下两个方向使用
        int[] obsUpInColums = new int[colCount];
        // 第j列的障碍物下边沿，给上下两个方向使用
        int[] obsDownInColums = new int[colCount];
        // 第i行的障碍物左边沿，给左右两个方向使用
        int[] obsLeftInRows = new int[rowCount];
        // 第i行的障碍物右边沿，给左右两个方向使用
        int[] obsRightInRows = new int[rowCount];
        constructColObs(rowCount, colCount, obstacle, obsUpInColums, obsDownInColums);
        constructRowObs(rowCount, colCount, obstacle, obsLeftInRows, obsRightInRows);
        for (int i = 0; i < directions.length(); i++) {
            if (directions.charAt(i) == 'U') {
                upDownDir(rowCount, irons, obsUpInColums, obsDownInColums, true);
            }
            if (directions.charAt(i) == 'D') {
                upDownDir(rowCount, irons, obsUpInColums, obsDownInColums, false);
            }
            if (directions.charAt(i) == 'L') {
                leftRightDir(colCount, irons, obsLeftInRows, obsRightInRows, true);
            }
            if (directions.charAt(i) == 'R') {
                leftRightDir(colCount, irons, obsLeftInRows, obsRightInRows, false);
            }
        }
        Arrays.sort(irons, new MyComparator());
        return irons;
    }

    private class MyComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b){
            if(a[0] != b[0]) return (a[0] < b[0]) ? -1: 1;
            else return (a[1] < b[1]) ? -1: 1;
        }
    }

    private void constructColObs(int rowCount, int colCount, int[] obstacle, int[] obsUpInColums,
                                 int[] obsDownInColums) {
        for (int j = 0; j < colCount; j++) {
            // 第j列的障碍物坐标
            int obsUp = rowCount;
            int obsDown = -1;
            if (j <= obstacle[3] && j >= obstacle[1]) {
                obsUp = obstacle[0];
                obsDown = obstacle[2];
            }
            obsUpInColums[j] = obsUp;
            obsDownInColums[j] = obsDown;
        }
    }

    private void constructRowObs(int rowCount, int colCount, int[] obstacle, int[] obsLeftInRows,
                                 int[] obsRightInRows) {
        for (int i = 0; i < rowCount; i++) {
            // 第i行的障碍物坐标
            int obsLeft = colCount;
            int obsRight = -1;
            if (i <= obstacle[2] && i >= obstacle[0]) {
                obsLeft = obstacle[1];
                obsRight = obstacle[3];
            }
            obsLeftInRows[i] = obsLeft;
            obsRightInRows[i] = obsRight;
        }
    }

    // 向上/向下，每次要用最新的irons位置重新计算
    private void upDownDir(int rowCount, int[][] irons, int[] obsUpInColums, int[] obsDownInColums, boolean isUp) {
        // 保存第j列在障碍物上面/下面的数量，没有key说明该列没有铁块
        Map<Integer, Integer> aboveObsCounts = new HashMap<>();
        Map<Integer, Integer> belowObsCounts = new HashMap<>();

        for (int i = 0; i < irons.length; i++) {
            int obsUp = obsUpInColums[irons[i][1]];
            int obsDown = obsDownInColums[irons[i][1]];
            if (irons[i][0] < obsUp) {
                aboveObsCounts.put(irons[i][1], aboveObsCounts.getOrDefault(irons[i][1], 0) + 1);
            } else if (irons[i][0] > obsDown) {
                belowObsCounts.put(irons[i][1], belowObsCounts.getOrDefault(irons[i][1], 0) + 1);
            } else {
                System.out.println("There has iron location error, row is " + irons[i][0] + " col is " + irons[i][1]);
            }
        }

        int i = 0;
        // 执行
        // 向上：障碍物上方的，从0行开始排
        // 向下：障碍物上方的，从obsUpInColums[column]-1行开始排
        for (Map.Entry<Integer, Integer> entry : aboveObsCounts.entrySet()) {
            int aboveObsCount = entry.getValue();
            for (int index = 0; index < aboveObsCount; index++) {
                if (isUp) {
                    irons[i][0] = index;
                    irons[i][1] = entry.getKey();
                } else {
                    irons[i][0] = obsUpInColums[entry.getKey()] - 1 - index;
                    irons[i][1] = entry.getKey();
                }
                i++;
            }
        }
        // 向上：障碍物下方的，从obsDownInColums[column]+1行开始排
        // 向下：障碍物下方的，从rowCount-1开始排
        for (Map.Entry<Integer, Integer> entry : belowObsCounts.entrySet()) {
            int belowObsCount = entry.getValue();
            for (int index = 1; index <= belowObsCount; index++) {
                if (isUp) {
                    irons[i][0] = obsDownInColums[entry.getKey()] + index;
                    irons[i][1] = entry.getKey();
                } else {
                    irons[i][0] = rowCount - index;
                    irons[i][1] = entry.getKey();
                }
                i++;
            }
        }

    }

    // 向左/向右，每次要用最新的irons位置重新计算
    private void leftRightDir(int colCount, int[][] irons, int[] obsLeftInRows, int[] obsRightInRows, boolean isLeft) {
        // 保存第i行在障碍物左面/右面的数量，没有key说明该行没有铁块
        Map<Integer, Integer> leftObsCounts = new HashMap<>();
        Map<Integer, Integer> rightObsCounts = new HashMap<>();

        for (int i = 0; i < irons.length; i++) {
            int obsLeft = obsLeftInRows[irons[i][0]];
            int obsRight = obsRightInRows[irons[i][0]];
            if (irons[i][1] < obsLeft) {
                leftObsCounts.put(irons[i][0], leftObsCounts.getOrDefault(irons[i][0], 0) + 1);
            } else if (irons[i][1] > obsRight) {
                rightObsCounts.put(irons[i][0], rightObsCounts.getOrDefault(irons[i][0], 0) + 1);
            } else {
                System.out.println("There has iron location error, row is " + irons[i][0] + " col is " + irons[i][1]);
            }
        }

        int i = 0;
        // 执行
        // 向左：障碍物左边的，从0列开始排
        // 向右：障碍物左边的，obsLeftInRows[row]-1行开始排
        for (Map.Entry<Integer, Integer> entry : leftObsCounts.entrySet()) {
            int leftObsCount = entry.getValue();
            for (int index = 0; index < leftObsCount; index++) {
                if (isLeft) {
                    irons[i][0] = entry.getKey();
                    irons[i][1] = index;
                } else {
                    irons[i][0] = entry.getKey();
                    irons[i][1] = obsLeftInRows[entry.getKey()] - 1 - index;
                    ;
                }
                i++;
            }
        }
        // 向左：障碍物右边的，obsRightInRows[row]+1行开始排
        // 向右：障碍物右边的，colCount-1开始排
        for (Map.Entry<Integer, Integer> entry : rightObsCounts.entrySet()) {
            int rightObsCount = entry.getValue();
            for (int index = 1; index <= rightObsCount; index++) {
                if (isLeft) {
                    irons[i][0] = entry.getKey();
                    irons[i][1] = obsRightInRows[entry.getKey()] + index;
                } else {
                    irons[i][0] = entry.getKey();
                    irons[i][1] = colCount - index;
                }
                i++;
            }
        }
    }
}
