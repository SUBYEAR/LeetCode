package com.leetcode.medium.review.backtrack;

/**
 * 累加数是一个字符串，组成它的数字可以形成累加序列。
 *
 * 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 *
 * 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。
 *
 * 说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 * <p></p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/additive-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode306_W {
    int record = 0;
    public boolean isAdditiveNumber(String num) {
        int num1 = 0;
        boolean res = false;
        for (int i = 0; i < num.length(); i++) {
            num1 = num1 * 10 +  num.charAt(i) - '0';
            int num2 = 0;
            for (int j = i + 1; j < num.length(); j++) {
                num2 = num2 * 10 +  num.charAt(j) - '0';
                if (backtrack(num, j + 1, num1, num2)) {
                    return true;
                }
                if (num2 == 0) {
                    break;
                }
            }
            if (num1 == 0) {
                break;
            }
        }
        return res;
    }

    boolean backtrack(String num, int start, int num1, int num2) {
        if (start == num.length()) {
            System.out.println("###递归到达终止条件！" + "[" + record +", " + num1 + ", " + num2 + "]");
            return (record + num1) == num2;
        }

        int total = 0;
        for (int i = start; i < num.length(); i++) {
            total = total * 10 + num.charAt(i) - '0';
            System.out.println("递归前，" + "num1:" + num1 + ", num2:" + num2 + ", total:" + total);
            if (num1 + num2 == total) {
                System.out.println("~~~~~~~~~~~~~");
                record = num1;
                if (backtrack(num, i + 1, num2, total)) {
                    return true;
                }
            }
            if (total == 0) {
                break;
            }
        }
        return false;
    }

    /**
     * 字符串
     */
    String s;
    /**
     * 字符串的长度
     */
    int n;

    public boolean isAdditiveNumber_another(String num) {
        this.s = num;
        this.n = num.length();
        return toFlashBack(0, 0, 0, 0);
    }

    /**
     * @param index    当前的下标
     * @param sum      前两个数的和
     * @param previous 前一个数的值
     * @param count    已生成几个数
     */
    public boolean toFlashBack(int index, long sum, long previous, int count) {
        // 如果已生成三个数及以上则返回 true
        if (index == n) {
            return count >= 3;
        }
        // 拼接数字的值，值可能越 int 界所以使用 long
        long value = 0;
        // 开始拼接数字
        for (int i = index; i < n; i++) {
            // 除 0 以外，其他数字第一位不能为 0
            if (i > index && s.charAt(index) == '0') {
                break;
            }
            // 计算数值
            value = value * 10 + s.charAt(i) - '0';
            // 判断数值是否合法，如果前面有两个以上的数，则判断前两个数的和是否等于这个数
            if (count >= 2) {
                if (value < sum) {
                    // 小的话继续向后继续拼接
                    continue;
                } else if (value > sum) {
                    // 大的话直接结束，再往后拼接无意义
                    break;
                }
            }
            // 使用该数，向下递归
            if (toFlashBack(i + 1, previous + value, value, count + 1)) {
                return true;
            }
            // 没有可恢复原样的变量
        }
        return false;
    }
}
