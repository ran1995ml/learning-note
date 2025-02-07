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
                List<Integer> pre = ans.get(i - 1);
                List<Integer> list = new ArrayList<>();
                list.add(1);
                for (int j = 1; j < i; j++) {
                    list.add(pre.get(j - 1) + pre.get(j));
                }
                list.add(1);
                ans.add(list);
            }
        }
        return ans;
    }
}
