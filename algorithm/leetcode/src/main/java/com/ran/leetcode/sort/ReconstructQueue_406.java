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
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o2[0] - o1[0];
            }
        });
        System.out.println(Arrays.deepToString(people));
        LinkedList<int[]> list = new LinkedList<>();
        for (int[] p : people) {
            int index = p[1];
            if (list.size() < index) {
                list.add(p);
            } else {
                list.add(index, p);
            }
        }
        return list.toArray(new int[][] {});
    }
}
