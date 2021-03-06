package com.leetcode.medium.review.binarysearch;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class LeetCode240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        return search(matrix, 0, 0, matrix[0].length - 1, matrix.length - 1, target);
    }

// 如果目标值包含在数组内，因此我们沿着索引行的矩阵中间列
// matrix[row-1][mid] < target < matrix[row][mid]（很明显，如果我们找到 target ，我们立即返回 true）。
// 现有的矩阵可以围绕这个索引分为四个子矩阵；左上和右下子矩阵不能包含目标（通过基本情况部分来判断），
// 所以我们可以从搜索空间中删除它们 。另外，左下角和右上角的子矩阵是二维矩阵，因此我们可以递归地将此算法应用于它们。
    boolean search(int[][] matrix, int left, int top, int right, int bottom, int target) {
        if (bottom < top || right < left) {
            return false;
        } else if (target < matrix[top][left] || target > matrix[bottom][right]) {
            return false;
        }
        int mid = left + (right - left) / 2;

        int row = top;
        while (row <= bottom && matrix[row][mid] <= target) {
            if (matrix[row][mid] == target) {
                return true;
            }
            row++;
        }

        return search(matrix, left, row, mid-1, bottom, target) || search(matrix, mid+1, top, right, row-1, target);
    }
// 从左下角开始往右上角搜索
// public boolean searchMatrix(int[][] matrix, int target) {
//        // start our "pointer" in the bottom-left
//        int row = matrix.length-1;
//        int col = 0;
//
//        while (row >= 0 && col < matrix[0].length) {
//            if (matrix[row][col] > target) {
//                row--;
//            } else if (matrix[row][col] < target) {
//                col++;
//            } else { // found it
//                return true;
//            }
//        }
//
//        return false;
//    }


    int binSearch(int[] arr, int target) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (arr[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}
