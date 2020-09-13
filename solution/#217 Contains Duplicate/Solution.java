package leetcode.q217.java;

import java.util.Arrays;
import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.platform.commons.util.StringUtils;

/**
 * <a href="https://leetcode-cn.com/problems/contains-duplicate/">
 * 217. Contains Duplicate
 * </a>
 */
class Solution {

    public boolean containsDuplicate(int[] nums) {
        if (nums.length >= 2) {
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(1, 2, 3, 1), true),
                Arguments.of(ints(1, 2, 3, 4), false),
                Arguments.of(ints(7, 6, 5, 4, 3, 2, 1, 2), true),
                Arguments.of(ints(3, 1), false),
                Arguments.of(ints(2, 14, 18, 22, 22), true),
                Arguments.of(ints(1, 1, 1, 3, 3, 4, 3, 2, 4, 2), true)
            );
        }
    }
}
