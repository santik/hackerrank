package longestsubstringwithoutrepeatingcharacters;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int lengthOfLongestSubstring(String s) {

        int length = s.length();
        int max = 0;
        int ind = 0;

        Set<Character> sub = new HashSet<>();

        for (int i = 0; i< length; i++) {
            char c = s.charAt(i);
            while (sub.contains(c)) {
                sub.remove(s.charAt(ind));
                ind ++;
            }

            sub.add(c);
            max = Math.max(i-ind + 1, max);
        }

        return Math.max(length-ind - 1 , max);

    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}