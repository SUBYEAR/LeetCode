/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 功能描述
 *
 * @since 2020-04-24
 */
public class Level {

    boolean isDigital(char character) {
        return character >= '0' && character <= '9';
    }

    public int levelSum(String input) {
        int level = 0;
        int num = 0;
        int sum = 0;
        List<int[]> info = new LinkedList<>();
        for (int index = 0; index < input.length(); index++) {
            char ch = input.charAt(index);
            if (isDigital(ch)) {
                num = num * 10 + (ch - '0'); // 乘10将字符串转换成数字
                if (!isDigital(input.charAt(index + 1))) {
                    info.add(new int[] {num, level});
                }
            } else if (ch == '(') {
                level += 1;
            } else if (ch == ')') {
                level -= 1;
            } else {
                num = 0;
            }
        }

        for (int[] arr : info) {
            sum += arr[0] * arr[1];
        }
        return sum;
    }
}
