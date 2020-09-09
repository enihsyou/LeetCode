package leetcode.q136.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

class Solution {

    public int singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(new int[]{ 1 }, 1),
                Arguments.of(new int[]{ 2, 2, 1 }, 1),
                Arguments.of(new int[]{ 4, 1, 2, 1, 2 }, 4)
            );
        }
    }
}
