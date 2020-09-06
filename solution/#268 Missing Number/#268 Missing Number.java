package leetcode.q268.java;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class Solution {

    public int missingNumber(int[] nums) {
        long sum       = nums.length * (nums.length + 1) / 2;
        long actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return (int) (sum - actualSum);
    }

    @SuppressWarnings("ZeroLengthArrayAllocation")
    static class SolutionTest {

        private final Solution solution = new Solution();

        @Test
        void test() {
            SoftAssertions.assertSoftly(soft -> {
                soft.assertThat(solution.missingNumber(new int[]{ 3, 0, 1 })).isEqualTo(2);
                soft.assertThat(solution.missingNumber(new int[]{ 9, 6, 4, 2, 3, 5, 7, 0, 1 })).isEqualTo(8);
                soft.assertThat(solution.missingNumber(new int[]{ })).isEqualTo(0);
            });
        }
    }
}

