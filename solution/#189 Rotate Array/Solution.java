package leetcode.q189.java;

import java.util.stream.Stream;

import leetcode.base.java.AssertMode;
import leetcode.base.java.JavaTest;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/rotate-array/">
 * 189. Rotate Array
 * </a>
 */
class Solution {

    public void rotate(int[] nums, int k) {
        // 多次循环和一次的效果一样
        k %= nums.length;

        int swapped = 0;
        for (int current = 0; swapped < nums.length; current++) {
            int pointer      = current;
            int numAtPointer = nums[pointer];
            do {
                // 要交换到到位置
                int shiftPointer = (pointer + k) % nums.length;
                // 要交换到到位置上到数字
                int numAtShift = nums[shiftPointer];
                // 旧数字交换到新位置
                nums[shiftPointer] = numAtPointer;
                // 保存被交换覆盖的数字
                swapped++;

                numAtPointer = numAtShift;
                pointer      = shiftPointer;
            } while (pointer != current);
        }
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected AssertMode assertMode() {
            return AssertMode.exceptArgumentMode(0);
        }

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(1, 2, 3, 4, 5, 6, 7), 3,
                             ints(5, 6, 7, 1, 2, 3, 4)),
                Arguments.of(ints(-1, -100, 3, 99), 2,
                             ints(3, 99, -1, -100)),
                Arguments.of(ints(1, 2), 0,
                             ints(1, 2))
            );
        }
    }
}
