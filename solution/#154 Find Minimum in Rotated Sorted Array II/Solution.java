package leetcode.q154.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/">
 * 154. Find Minimum in Rotated Sorted Array II
 * </a>
 */
class Solution {

    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums[0] < nums[nums.length - 1]) {
            return nums[0];
        }

        int start = 0, right = nums.length - 1;
        while (start < right) { // break when start = right
            int middle = (start + right) / 2;

            if (nums[middle] > nums[middle + 1]) {
                return nums[middle + 1];
            }
            if (nums[middle - 1] > nums[middle]) {
                return nums[middle];
            }
            if (nums[middle] > nums[start]) {
                start = middle + 1;
            } else if (nums[middle] == nums[start]) {
                start++;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    public int findMin2(int[] nums) {
        int start = 0, right = nums.length - 1;
        while (start < right) { // break when start = right
            int middle = (start + right) / 2;
            if (nums[middle] > nums[right]) {
                start = middle + 1;
            } else if (nums[middle] == nums[right]) {
                right--;
            } else {
                right = middle;
            }
        }
        assert start == right;
        return nums[start];
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(1), 1),
                Arguments.of(ints(2, 1), 1),
                Arguments.of(ints(3, 1, 2), 1),
                Arguments.of(ints(1, 2, 0), 0),
                Arguments.of(ints(1, 2, 3), 1),
                Arguments.of(ints(4, 5, 1, 2, 3), 1),
                Arguments.of(ints(3, 4, 5, 1, 2), 1),
                Arguments.of(ints(4, 5, 6, 7, 0, 1, 2), 0),
                Arguments.of(ints(1, 3, 4), 1),
                Arguments.of(ints(1, 3, 3), 1),
                Arguments.of(ints(1, 3, 3, 3), 1),
                Arguments.of(ints(3, 3, 3, 1), 1),
                Arguments.of(ints(1, 1, 3, 3), 1),
                Arguments.of(ints(2, 2, 2, 0, 1), 0),
                Arguments.of(ints(2, 5, 6, 0, 0, 1, 2), 0),
                Arguments.of(ints(0, 0, 0, 0, 0, 1, 2), 0),
                Arguments.of(ints(0, 1, 0, 0, 0, 0, 0), 0),
                Arguments.of(ints(2, 2, 2, 2, 2, 1, 2), 1),
                Arguments.of(ints(2, 5, 6, 7, 8, 1, 2), 1)
            );
        }
    }
}
