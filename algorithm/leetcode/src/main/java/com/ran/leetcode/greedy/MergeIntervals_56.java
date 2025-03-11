package com.ran.leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * MergeIntervals_56
 *
 * @author rwei
 * @since 2024/6/11 10:48
 */
public class MergeIntervals_56 {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        MergeIntervals_56 obj = new MergeIntervals_56();
        System.out.println(Arrays.deepToString(obj.merge(intervals)));
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> ans = new ArrayList<>();
        for (int[] interval : intervals) {
            if (ans.isEmpty()) {
                ans.add(interval);
            } else {
                if (interval[0] > ans.get(ans.size() - 1)[1]) {
                    ans.add(interval);
                } else {
                    int end = Math.max(interval[1], ans.get(ans.size() - 1)[1]);
                    ans.get(ans.size() - 1)[1] = end;
                }
            }
        }
        return ans.toArray(new int[][] {});
    }
}
