package com.ran.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * PascalTriangle_118
 *
 * @author rwei
 * @since 2024/12/22 16:19
 */
public class PascalTriangle_118 {
    public static void main(String[] args) {
        PascalTriangle_118 obj = new PascalTriangle_118();
        int numRows = 5;
        System.out.println(obj.generate(numRows));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            if (i == 0) {
                ans.add(Collections.singletonList(1));
            } else if (i == 1) {
                ans.add(Arrays.asList(1, 1));
            } else {
                List<Integer> cur = new ArrayList<>();
                List<Integer> pre = ans.get(i - 1);
                cur.add(1);
                for (int j = 0; j < pre.size(); j++) {
                    if (j + 1 < pre.size()) {
                        cur.add(pre.get(j) + pre.get(j + 1));
                    }
                }
                cur.add(1);
                ans.add(cur);
            }
        }
        return ans;
    }
}
