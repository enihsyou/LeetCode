package leetcode.q198.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/house-robber/">
 * 198. House Robber
 * </a>
 */
class Solution {

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        sums[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            sums[i] = Math.max(sums[i - 2] + nums[i], sums[i - 1]);
        }
        return sums[nums.length - 1];
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(1, 2, 3, 1), 4),
                Arguments.of(ints(2, 1, 1, 2), 4),
                Arguments.of(ints(2, 7, 9, 3, 1), 12)
            );
        }
    }
}
