package leetcode.q1000139.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/nGK0Fy/">
 *     LCP 17. 速算机器人
 * </a>
 */
class Solution {
    public int calculate(String s) {
        int x = 1, y = 0;
        for (char c : s.toCharArray()) {
            if (c == 'A') {
                x = 2 * x + y;
            } else if (c == 'B') {
                y = 2 * y + x;
            }
        }

        return x + y;
    }

    public int calculate2(String s) {
        return 1 << s.length();
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of("A", 2),
                Arguments.of("B", 2),
                Arguments.of("AB", 4),
                Arguments.of("BA", 4)
            );
        }
    }
}
