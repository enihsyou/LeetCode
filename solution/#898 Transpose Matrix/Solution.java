package leetcode.q898.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

class Solution {

    public int[][] transpose(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;

        int[][] B = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                B[j][i] = A[i][j];
            }
        }
        return B;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(ints(1, 2, 3), ints(4, 5, 6), ints(7, 8, 9)),
                             ints(ints(1, 4, 7), ints(2, 5, 8), ints(3, 6, 9))),
                Arguments.of(ints(ints(1, 2, 3), ints(4, 5, 6)),
                             ints(ints(1, 4), ints(2, 5), ints(3, 6)))
            );
        }
    }
}
