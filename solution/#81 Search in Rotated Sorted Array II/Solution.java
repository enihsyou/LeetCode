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

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == target) {
                return true;
            }
            if (nums[i] < nums[i - 1]) {
                return search(nums, target, i);
            }
        }
        return nums[0] == target;
    }


    private static boolean search(int[] nums, int target, int offset) {
        int arrow0 = offset, arrow9 = nums.length - 1 + offset;
        while (arrow0 <= arrow9) {
            int arrow5 = (arrow0 + arrow9) / 2;
            if (nums[arrow5 % nums.length] == target) {
                return true;
            }
            if (nums[arrow5 % nums.length] < target) {
                arrow0 = arrow5 + 1;
            } else {
                arrow9 = arrow5 - 1;
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
