package leetcode.q137.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

class Solution {

    public int singleNumber1(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones ^= num & ~twos;
            twos ^= num & ~ones;
        }
        return ones;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(new int[]{ 2, 2, 3, 2 }, 3),
                Arguments.of(new int[]{ 0, 1, 0, 1, 0, 1, 99 }, 99),
                Arguments.of(new int[]{ 43, 16, 45, 89, 45, -2147483648, 45, 2147483646, -2147483647, -2147483648, 43,
                                        2147483647, -2147483646, -2147483648, 89, -2147483646, 89, -2147483646,
                                        -2147483647,
                                        2147483646, -2147483647, 16, 16, 2147483646, 43 }, 2147483647)
            );
        }
    }
}
