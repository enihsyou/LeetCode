package leetcode.q20.java;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class Solution {

    private static final char[] open = {'(', '{', '['};

    private static final char[] close = {')', '}', ']'};

    public boolean isValid(String s) {
        int stack_size = (s.length() + 1) / 2;
        int[] left = new int[stack_size];
        int length = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            int open = containOpen(c);
            if (open >= 0) {
                /*Fail fast*/
                if (length >= stack_size) return false;

                left[length] = open;
                ++length;
                continue;
            }

            int close = containClose(c);
            if (close >= 0) {
                --length;
                if (length < 0 || left[length] != close) return false;
                continue;
            }

            throw new IllegalArgumentException(String.valueOf(c));
        }

        return length == 0;
    }

    private static int containOpen(char c) {
        int bound = open.length;
        for (int i = 0; i < bound; ++i) {
            if (c == open[i]) return i;
        }
        return -1;
    }

    private static int containClose(char c) {
        int bound = close.length;
        for (int i = 0; i < bound; ++i) {
            if (c == close[i]) return i;
        }
        return -1;
    }

    static class SolutionTest {

        private final Solution solution = new Solution();

        @Test
        void test() {
            SoftAssertions.assertSoftly(soft -> {
                soft.assertThat(solution.isValid("")).isTrue();
                soft.assertThat(solution.isValid("()")).isTrue();
                soft.assertThat(solution.isValid("()[]{}")).isTrue();
                soft.assertThat(solution.isValid("(]")).isFalse();
                soft.assertThat(solution.isValid("(((((")).isFalse();
                soft.assertThat(solution.isValid("([)]")).isFalse();
                soft.assertThat(solution.isValid("{[]}")).isTrue();
            });
        }
    }

}
