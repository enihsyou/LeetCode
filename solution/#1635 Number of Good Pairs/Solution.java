package leetcode.q1635.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/number-of-good-pairs/">
 * 1512. Number of Good Pairs
 * </a>
 */
class Solution {

    public int numIdenticalPairs(int[] nums) {
        byte[] count = new byte[101];
        for (int num : nums) {
            count[num]++;
        }

        int answer = 0;
        for (byte b : count) {
            if (b > 1) {
                answer += group(b, 2);
            }
        }
        return answer;
    }

    private static int group(int total, int select) {
        int numerator   = 1;
        int denominator = 1;

        for (int i = total; i > total - select; i--) {
            numerator *= i;
        }

        for (int i = select; i > 0; i--) {
            denominator *= i;
        }

        return (numerator / denominator);
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(1, 2, 3, 1, 1, 3), 4),
                Arguments.of(ints(1, 1, 1, 1), 6),
                Arguments.of(ints(1, 2, 3), 0)
            );
        }
    }
}
