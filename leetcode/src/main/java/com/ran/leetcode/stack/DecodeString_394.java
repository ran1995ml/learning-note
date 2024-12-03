package com.ran.leetcode.stack;

import java.util.Stack;

/**
 * DecodeString_394
 *
 * @author rwei
 * @since 2024/11/12 15:41
 */
public class DecodeString_394 {
    public static void main(String[] args) {
        DecodeString_394 obj = new DecodeString_394();
        String s = "3[a2[c]]";
        System.out.println(obj.decodeString(s));
    }

    public String decodeString(String s) {
        char[] ch = s.toCharArray();
        Stack<Integer> stackNum = new Stack<>();
        Stack<String> stackStr = new Stack<>();
        StringBuffer sbNum = new StringBuffer();
        StringBuffer sbStr = new StringBuffer();

        for (char c : ch) {
            if (c == '[') {
                stackNum.push(Integer.parseInt(sbNum.toString()));
                stackStr.push(sbStr.toString());
                sbNum.setLength(0);
                sbStr.setLength(0);
            } else if (c == ']') {
                int curNum = stackNum.pop();
                String curStr = stackStr.pop();
                StringBuffer temp = new StringBuffer();

                for (int i = 0; i < curNum; i++) {
                    temp.append(sbStr);
                }
                sbStr = new StringBuffer(curStr + temp);
            } else if (c >= '0' && c <= '9') {
                sbNum.append(c);
            } else {
                sbStr.append(c);
            }
        }
        return sbStr.toString();
    }
}
