package leetcode.q66.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

class Solution {

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                return digits;
            }
        }
        int[] dest = new int[digits.length + 1];
        dest[0] = 1;
        return dest;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(new int[]{ 1, 2, 3 }, new int[]{ 1, 2, 4 }),
                Arguments.of(new int[]{ 3, 2, 1 }, new int[]{ 3, 2, 2 }),
                Arguments.of(new int[]{ 9, 9 }, new int[]{ 1, 0, 0 }),
                Arguments.of(new int[]{ 9 }, new int[]{ 1, 0 }),
                Arguments.of(new int[]{ 0 }, new int[]{ 1 })
            );
        }
    }
}
