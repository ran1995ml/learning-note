package com.ran.leetcode.map;

import java.util.*;

/**
 * GroupAnagrams_49
 *
 * @author rwei
 * @since 2024/6/3 10:04
 */
public class GroupAnagrams_49 {
    public static void main(String[] args) {
        GroupAnagrams_49 obj = new GroupAnagrams_49();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(obj.groupAnagrams(strs));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
