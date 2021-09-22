package com.leetcode.hard.bitwise;

/**
 * 列表的 异或和（XOR sum）指对所有元素进行按位 XOR 运算的结果。如果列表中仅有一个元素，那么其 异或和 就等于该元素。
 *
 * 例如，[1,2,3,4] 的 异或和 等于 1 XOR 2 XOR 3 XOR 4 = 4 ，而 [3] 的 异或和 等于 3 。
 * 给你两个下标 从 0 开始 计数的数组 arr1 和 arr2 ，两数组均由非负整数组成。
 *
 * 根据每个 (i, j) 数对，构造一个由 arr1[i] AND arr2[j]（按位 AND 运算）结果组成的列表。其中 0 <= i < arr1.length 且 0 <= j < arr2.length 。
 *
 * 返回上述列表的 异或和 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-xor-sum-of-all-pairs-bitwise-and
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1835 {
    final int HIGH_BIT = 30;
    public int getXORSum(int[] arr1, int[] arr2) {
        // 10^9的最高比特位是2^30
        int[] bits = new int[HIGH_BIT];
        for (int i = 0; i < arr2.length; i++) {
            int[] arr = getValBits(arr2[i]);
            arrAdd(bits, arr);
        }

        int ans = 0;
        for (int i = 0; i < arr1.length; i++) {
//            int val = arrBitAndOr(Arrays.copyOf(arr2, arr2.length), arr1[i]);
            int[] mask = getValBits(arr1[i]);
            int[] valArr = new int[HIGH_BIT];
            for (int j = 0; j < mask.length; j++) {
                valArr[j] = (mask[j] != 0 &&  (bits[j] % 2 == 1)) ? 1 : 0;
            }

            int val2 = 0;
            for (int j = 0; j < valArr.length; j++) {
                if (valArr[j] == 0) {
                    continue;
                }

                val2 |= (1 << j);
            }
            ans ^= val2;
        }

        return ans;
    }

    void arrAdd(int[] a1, int[] a2) {
        for (int i = 0; i < a1.length; i++) {
            a1[i] += a2[i];
        }
    }

    int[] getValBits(int val) {
        int[] bits = new int[HIGH_BIT];
        for (int i = bits.length - 1; i >= 0; i--) {
            if ((val & (1 << i)) != 0) {
                bits[i] = 1;
            }
        }
//        String bs = Integer.toBinaryString(val);
        return bits;
    }
}
