package com.ran.leetcode.sort;

import sun.awt.image.ImageWatched;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * ReconstructQueue_406
 *
 * @author rwei
 * @since 2024/11/13 14:28
 */
public class ReconstructQueue_406 {
    public static void main(String[] args) {
        ReconstructQueue_406 obj = new ReconstructQueue_406();
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        System.out.println(Arrays.deepToString(obj.reconstructQueue(people)));
    }

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                if (p1[0] == p2[0]) {
                    return p1[1] - p2[1];
                } else {
                    return p2[0] - p1[0];
                }
            }
        });

        LinkedList<int[]> ans = new LinkedList<>();
        for (int[] p : people) {
            int index = p[1];
            if (ans.size() < index) {
                ans.add(p);
            } else {
                ans.add(index, p);
            }
        }
        return ans.toArray(new int[][] {});
    }
}
