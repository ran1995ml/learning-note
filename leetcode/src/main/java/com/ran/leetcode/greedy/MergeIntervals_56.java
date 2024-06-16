package com.ran.leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MergeIntervals_56
 *
 * @author rwei
 * @since 2024/6/11 10:48
 */
public class MergeIntervals_56 {
    public static void main(String[] args) {
        int[][] intervals = {{1, 4}, {2, 3}};
        MergeIntervals_56 obj = new MergeIntervals_56();
        System.out.println(Arrays.deepToString(obj.merge(intervals)));
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> ans = new ArrayList<>();

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (ans.size() == 0 || start > ans.get(ans.size() - 1)[1]) {
                ans.add(new int[]{start, end});
            } else {
                ans.get(ans.size() - 1)[1] = Math.max(end, ans.get(ans.size() - 1)[1]);
            }
        }

        return ans.toArray(new int[][]{});
    }
}
