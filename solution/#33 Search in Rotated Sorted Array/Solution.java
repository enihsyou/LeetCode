package leetcode.q33.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/search-in-rotated-sorted-array/">
 * 33. Search in Rotated Sorted Array
 * </a>
 */
class Solution {

    public int search(int[] nums, int target) {
        int nums0 = nums[0];
        int nums9 = nums[nums.length - 1];

        int start = 0, right = nums.length - 1;
        while (start <= right) {
            int middle = (start + right) / 2;
            int nums5  = nums[middle];
            if (nums5 == target) {
                return middle;
            }

            if (nums0 <= nums5) {
                if (nums0 <= target && target < nums5) {
                    right = middle - 1;
                } else {
                    start = middle + 1;
                }
            } else {
                if (nums5 < target && target <= nums9) {
                    start = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(0, 1, 2, 4, 5, 6, 7), 0, 0),
                Arguments.of(ints(0, 1, 2, 4, 5, 6, 7), 4, 3),
                Arguments.of(ints(4, 5, 6, 7, 0, 1, 2), 7, 3),
                Arguments.of(ints(4, 5, 6, 7, 0, 1, 2), 0, 4),
                Arguments.of(ints(4, 5, 6, 7, 0, 1, 2), 1, 5),
                Arguments.of(ints(4, 5, 6, 7, 0, 1, 2), 3, -1),
                Arguments.of(ints(3, 1), 3, 0),
                Arguments.of(ints(3, 1), 1, 1),
                Arguments.of(ints(1, 3), 1, 0),
                Arguments.of(ints(1, 3), 3, 1)
            );
        }
    }
}
