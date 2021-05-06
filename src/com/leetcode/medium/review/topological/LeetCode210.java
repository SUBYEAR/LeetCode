package com.leetcode.medium.review.topological;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 *
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 *
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 2021-03-02
 */
public class LeetCode210 {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		LinkedList<Integer>[] adj = new LinkedList[numCourses];
		int[] indegree = new int[numCourses];
		for (int i = 0; i < numCourses; i++) {
			adj[i] = new LinkedList<>();
		}
		for (int[] pre : prerequisites) {
			int i = pre[0];
			int j = pre[1];
			adj[j].add(i);
			indegree[i]++;
		}

		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				st.push(i);
			}
		}

		int[] res = new int[numCourses];
		int count = 0;
		while (!st.isEmpty()) {
			int cur = st.pop();
			res[count++] = cur;

			for (int e : adj[cur]) {
				--indegree[e];
				if (indegree[e] == 0) {
					st.push(e);
				}
			}
		}
		return count == numCourses ? res : new int[0];
	}
}
