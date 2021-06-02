package com.leetcode.medium.review;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 给定一幅由黑色像素和白色像素组成的图像， 与一个正整数N, 找到位于某行 R 和某列 C 中且符合下列规则的黑色像素的数量:
 *
 * 行R 和列C都恰好包括N个黑色像素。
 * 列C中所有黑色像素所在的行必须和行R完全相同。
 * 图像由一个由‘B’和‘W’组成二维字符数组表示, ‘B’和‘W’分别代表黑色像素和白色像素。
 *
 * 示例:
 *
 * 输入:
 * [['W', 'B', 'W', 'B', 'B', 'W'],
 *  ['W', 'B', 'W', 'B', 'B', 'W'],
 *  ['W', 'B', 'W', 'B', 'B', 'W'],
 *  ['W', 'W', 'B', 'W', 'B', 'W']]
 *
 * N = 3
 * 输出: 6
 * 解析: 所有粗体的'B'都是我们所求的像素(第1列和第3列的所有'B').
 *         0    1    2    3    4    5         列号
 * 0    [['W', 'B', 'W', 'B', 'B', 'W'],
 * 1     ['W', 'B', 'W', 'B', 'B', 'W'],
 * 2     ['W', 'B', 'W', 'B', 'B', 'W'],
 * 3     ['W', 'W', 'B', 'W', 'B', 'W']]
 * 行号
 *
 * 以R = 0行和C = 1列的'B'为例:
 * 规则 1，R = 0行和C = 1列都恰好有N = 3个黑色像素.
 * 规则 2，在C = 1列的黑色像素分别位于0，1和2行。它们都和R = 0行完全相同。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lonely-pixel-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode533_W {
    // 第二个限制条件其实是指N行的B元素的位置要完全一致
    public int findBlackPixel(char[][] picture, int N) {
        int row = picture.length;
        int col = row == 0 ? 0 : picture[0].length;
        if (col == 0) {
            return 0;
        }
        // 条件2：要保证这N行的黑元素的位置要完全一样，可以一个一个的来比较。
        // 好的做法是把行元素转换成字符串或tuple，用一个哈希表记录行元素，然后直接比较行。
        List<String> rowString = new ArrayList<>();
        int[] numBlackRow = new int[row];
        int[] numBlackCol = new int[col];

        for (int i = 0; i < row; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < col; j++) {
                if (picture[i][j] == 'B') {
                    numBlackRow[i]++;
                    numBlackCol[j]++;
                }
                sb.append(picture[i][j]);
            }
            rowString.add(sb.toString());
        }

        int count = 0;
        HashSet<Integer> colVisited = new HashSet<>();
        for (int j = 0; j < col; j++) {
            if (numBlackCol[j] != N) { // 每一列要有N个B像素
                continue;
            }
            for (int i = 0; i < row; i++) {
                if (numBlackRow[i] != numBlackCol[j] || colVisited.contains(j)) { // 每一行要有N个B像素
                    continue; // 这里没有colVisit标记的话在0行处理时col列满足结果，在处理1行时col列也满足结果就会导致重复计算
                }

                HashSet<String> visited = new HashSet<>();
                for (int k = 0; k < row; k++) {
                    if (picture[k][j] == 'B') {
                        visited.add(rowString.get(k));
                    }
                }

                if (visited.size() == 1) {
                    count += numBlackCol[j];
                    colVisited.add(j);
                }
            }
        }

        return count;
    }
}
