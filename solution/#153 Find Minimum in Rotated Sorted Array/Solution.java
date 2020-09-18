package leetcode.q153.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/">
 * 153. Find Minimum in Rotated Sorted Array
 * </a>
 */
class Solution {

    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int start = 0, right = nums.length - 1;
        while (start < right) { // break when start = right
            int middle = (start + right) / 2;
            int nums0  = nums[start];
            int nums5  = nums[middle];
            int nums9  = nums[right];

            if (nums0 > nums9) {
                if (nums0 < nums5) {
                    start = middle + 1;
                } else {
                    right = middle;
                }
            } else if (nums0 == nums9) {
                start++;
            } else /*if (nums0 < nums9)*/ {
                return nums0;
            }
        }
        if (start + 1 < nums.length) {
            return Math.min(nums[start], nums[start + 1]);
        } else {
            return Math.min(nums[start], nums[start - 1]);
        }
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(1), 1),
                Arguments.of(ints(2, 1), 1),
                Arguments.of(ints(3, 1, 2), 1),
                Arguments.of(ints(1, 2, 0), 0),
                Arguments.of(ints(4, 5, 1, 2, 3), 1),
                Arguments.of(ints(3, 4, 5, 1, 2), 1),
                Arguments.of(ints(4, 5, 6, 7, 0, 1, 2), 0)
            );
        }
    }
}
