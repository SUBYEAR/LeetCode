package com.leetcode.hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 *
 * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
 *
 * 单词 word 中包含谜面 puzzle 的第一个字母。
 * 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）都不能作为谜底。
 * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 2021-02-27
 */
public class LeetCode1178 {
	public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
		List<Integer> res = new LinkedList<>();
		for (String puzzle : puzzles) {
			res.add(getMiddle(words, puzzle));
		}
		return res;
	}

	int  getMiddle(String[] words, String puzzle) {
		int num = 0;
		for (String word : words) {
			if (!word.contains(puzzle.charAt(0) + "")) {
				continue;
			}

			Set<Character> wordSet = getCharSet(word);
			if (!getCharSet(puzzle).addAll(wordSet)) {
				++num;
			}
		}

		return num;
	}

	Set<Character> getCharSet(String s) {
		Character[] charObjectArray =
			s.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
		return Arrays.stream(charObjectArray).collect(Collectors.toSet());
	}

	public int trans(String puzzle) {
		int res = Integer.MAX_VALUE;
		int a = 0;
		for (int i = 0; i < puzzle.length(); i++) {
			int temp = (puzzle.charAt(i) - 'a') << (4 * i);
			a += temp;
			System.out.printf("%x, %x\n", temp, a);
		}
		return (res - 0x7000_0000) & a;
	}

}
