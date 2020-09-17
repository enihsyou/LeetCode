package leetcode.q35.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/search-insert-position/">
 * 35. Search Insert Position
 * </a>
 */
class Solution {

    public int searchInsert(int[] nums, int target) {
        int lower = 0, right = nums.length - 1;

        while (lower <= right) {
            int middle = (lower + right) / 2;

            int middleValue = nums[middle];
            if (middleValue == target) {
                return middle;
            }
            if (middleValue < target) {
                lower = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return lower;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(1, 3, 5, 6), 5, 2),
                Arguments.of(ints(1, 3, 5, 6), 2, 1),
                Arguments.of(ints(1, 3, 5, 6), 7, 4),
                Arguments.of(ints(1, 3, 5, 6), 0, 0),
                Arguments.of(ints(1, 3, 5, 6, 9), 8, 4)
            );
        }
    }
}
