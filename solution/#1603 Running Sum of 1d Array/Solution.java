package leetcode.q1603.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/running-sum-of-1d-array/">
 * 1480. Running Sum of 1d Array
 * </a>
 */
class Solution {

    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(1, 2, 3, 4), ints(1, 3, 6, 10)),
                Arguments.of(ints(1, 1, 1, 1, 1), ints(1, 2, 3, 4, 5)),
                Arguments.of(ints(3, 1, 2, 10, 1), ints(3, 4, 6, 16, 17)),
                Arguments.of(ints(1, -1, 2, -1), ints(1, 0, 2, 1))

            );
        }
    }
}
