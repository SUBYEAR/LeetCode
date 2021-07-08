package com.leetcode.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。
 *
 * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 *
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
 *
 * 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
 *
 * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 *
 * 给定一个化学式 formula ，返回所有原子的数量。格式为：第一个（按字典序）原子的名字，跟着它的数量（如果数量大于 1），
 * 然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-atoms
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode726 {
    int index = 0;

    public String countOfAtoms(String formula) {
        int len = formula.length();
        StringBuilder s = new StringBuilder();
        Map<String, Integer> freq = new HashMap<>();

        while (index < len) {
            char c = formula.charAt(index);
            if (c == '(') {
                s.append(c);
                index++;
            } else if (Character.isUpperCase(c)) {
                s.append(c);
                ++index;
                while (index < len && Character.isLowerCase(formula.charAt(index))) {
                    s.append(formula.charAt(index));
                    index++;
                }
                String atom = getLastAtom(s);
                int times = 0;
                if (index < len && Character.isDigit(formula.charAt(index))) {
                    times = getDigit(formula);
                }
                freq.put(atom, freq.getOrDefault(atom, 1) + times);
            } else if (c == ')') {
                ++index;
                int times = getDigit(formula);
                int i = s.length() - 1;
                while (i >= 0 && s.charAt(i) != '(') {
                    i--;
                }
                String atoms = s.substring(i + 1, s.length());
                s.deleteCharAt(i);

                if (times > 1) {
                    process(atoms, freq, times);
                }
            }
        }
        return "";
    }

    private String getLastAtom(StringBuilder s) {
        int i = s.length() - 1;
        while (i >= 0 && !Character.isUpperCase(s.charAt(i))) {
            i--;
        }
        return s.substring(i, s.length());
    }

    private void process(String atoms, Map<String, Integer> freq, int digit) {
        int j = 0;
        int pre = 0;
        String atom;
        while (++j < atoms.length()) {
            if (Character.isLowerCase(atoms.charAt(j))) {
                continue;
            }
            atom = atoms.substring(pre, j);
            freq.put(atom, freq.get(atom) * digit);
            pre = j;
        }
        atom = atoms.substring(pre, j);
        freq.put(atom, freq.get(atom) * digit);
    }

    private int getDigit(String formula) {
        int times = 0;
        while (index < formula.length() && Character.isDigit(formula.charAt(index))) {
            times = times * 10 + formula.charAt(index) - '0';
            index++;
        }
        return times;
    }

    private String process(StringBuilder s) {
        int index = 0;
        int len = s.length();
        TreeMap<String, Integer> freq = new TreeMap<>();
        while (index < len) {
            char c = s.charAt(index);
            int start = index;
            if (Character.isUpperCase(c)) {
                index++;
                while (index < len && Character.isLowerCase(s.charAt(index))) {
                    index++;
                }
            }
            String atom = s.substring(start, index);
            freq.put(atom, freq.getOrDefault(atom, 0) + 1);
        }
        s.setLength(0);
        for (Map.Entry<String, Integer> e : freq.entrySet()) {
            s.append(e.getKey());
            if (e.getValue() > 1) {
                s.append(e.getValue());
            }
        }
        return s.toString();
    }
}
