package leetcode.q81.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/">
 * 81. Search in Rotated Sorted Array II
 * </a>
 */
class Solution {

    public boolean search(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }

        int start = 0, right = nums.length - 1;
        while (start <= right) {
            int middle = (start + right) / 2;
            int nums0  = nums[start];
            int nums9  = nums[right];
            int nums5  = nums[middle];
            if (nums5 == target) {
                return true;
            }

            if (nums0 < nums5) {
                if (nums0 <= target && target < nums5) {
                    right = middle - 1;
                } else {
                    start = middle + 1;
                }
            } else if (nums0 == nums5) {
                start++;
            } else {
                if (nums5 < target && target <= nums9) {
                    start = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return false;
    }


    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(2, 5, 6, 0, 0, 1, 2), 0, true),
                Arguments.of(ints(0, 0, 0, 0, 0, 1, 2), 0, true),
                Arguments.of(ints(0, 1, 0, 0, 0, 0, 0), 1, true),
                Arguments.of(ints(2, 2, 2, 2, 2, 1, 2), 1, true),
                Arguments.of(ints(2, 5, 6, 0, 0, 1, 2), 3, false),
                Arguments.of(emptyInts(), 3, false),
                Arguments.of(ints(1), 3, false),
                Arguments.of(ints(3, 1), 0, false)
            );
        }
    }
}
