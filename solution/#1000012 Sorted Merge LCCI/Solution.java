package leetcode.q1000012.java;

import java.util.stream.Stream;

import leetcode.base.java.AssertMode;
import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/sorted-merge-lcci/">
 * 面试题 10.01. Sorted Merge LCCI
 * </a>
 */
class Solution {

    public void merge(int[] A, int m, int[] B, int n) {
        int pointerInA  = m - 1;
        int pointerInB  = n - 1;
        int insertPoint = m + n - 1;

        while (insertPoint >= 0) {
            int valueInA = pointerInA >= 0 ? A[pointerInA] : Integer.MIN_VALUE;
            int valueInB = pointerInB >= 0 ? B[pointerInB] : Integer.MIN_VALUE;

            if (valueInA <= valueInB) {
                A[insertPoint--] = valueInB;
                pointerInB--;
            } else {
                A[insertPoint--] = valueInA;
                pointerInA--;
            }
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
                Arguments.of(ints(1, 2, 3, 0, 0, 0), 3, ints(2, 5, 6), 3,
                             ints(1, 2, 2, 3, 5, 6)),
                Arguments.of(ints(4, 5, 6, 0, 0, 0), 3, ints(2, 5, 6), 3,
                             ints(2, 4, 5, 5, 6, 6)),
                Arguments.of(ints(4, 5, 6, 0, 0, 0), 3, ints(1, 2, 3), 3,
                             ints(1, 2, 3, 4, 5, 6))
            );
        }
    }
}
