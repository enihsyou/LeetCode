package leetcode.q48.java;

import java.util.stream.Stream;

import leetcode.base.java.AssertMode;
import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/rotate-image/">
 * 48. Rotate Image
 * </a>
 */
class Solution {

    public void rotate(int[][] matrix) {
        int maxLevel = matrix.length / 2;
        for (int level = 0; level < maxLevel; level++) {
            walk(matrix, level);
        }
    }

    private static void walk(int[][] matrix, int margin) {
        int bottom = matrix.length - 1;

        for (int i = margin; i < matrix.length - 1 - margin; i++) {
            int pick = matrix[margin][i];

            matrix[margin][i]                   = matrix[bottom - i][margin];
            matrix[bottom - i][margin]          = matrix[bottom - margin][bottom - i];
            matrix[bottom - margin][bottom - i] = matrix[i][bottom - margin];
            matrix[i][bottom - margin]          = pick;
            // [bottom - margin][bottom - i]与[margin][i]关于中心点对称，所以用bottom去减
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
                Arguments.of(arrayInts(ints(1)), arrayInts(ints(1))),
                Arguments.of(ints(ints(1, 2),
                                  ints(3, 4)), ints(ints(3, 1),
                                                    ints(4, 2))),
                Arguments.of(ints(ints(1, 2, 3),
                                  ints(4, 5, 6),
                                  ints(7, 8, 9)), ints(ints(7, 4, 1),
                                                       ints(8, 5, 2),
                                                       ints(9, 6, 3))),
                Arguments.of(ints(ints(5, 1, 9, 11),
                                  ints(2, 4, 8, 10),
                                  ints(13, 3, 6, 7),
                                  ints(15, 14, 12, 16)), ints(ints(15, 13, 2, 5),
                                                              ints(14, 3, 4, 1),
                                                              ints(12, 6, 8, 9),
                                                              ints(16, 7, 10, 11))),
                Arguments.of(ints(ints(1, 2, 3, 4, 5),
                                  ints(6, 7, 8, 8, 10),
                                  ints(11, 12, 13, 14, 15),
                                  ints(16, 17, 18, 19, 20),
                                  ints(21, 22, 23, 24, 25)), ints(ints(21, 16, 11, 6, 1),
                                                                  ints(22, 17, 12, 7, 2),
                                                                  ints(23, 18, 13, 8, 3),
                                                                  ints(24, 19, 14, 8, 4),
                                                                  ints(25, 20, 15, 10, 5)))
            );
        }
    }
}
