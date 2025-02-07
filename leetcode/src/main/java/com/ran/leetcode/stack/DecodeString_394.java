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
        StringBuffer sbStr = new StringBuffer();
        StringBuffer sbNum = new StringBuffer();
        Stack<String> stackStr = new Stack<>();
        Stack<Integer> stackNum = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '[') {
                stackStr.push(sbStr.toString());
                stackNum.push(Integer.parseInt(sbNum.toString()));
                sbStr.setLength(0);
                sbNum.setLength(0);
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
