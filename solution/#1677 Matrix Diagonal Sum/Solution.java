package leetcode.q1677.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/matrix-diagonal-sum/">
 * 1572. Matrix Diagonal Sum
 * </a>
 */
class Solution {

    public int diagonalSum(int[][] mat) {
        int answer = 0;
        for (int i = 0; i < mat.length; i++) {
            answer += mat[i][i];
            answer += mat[i][mat.length - 1 - i];
        }
        if ((mat.length & 1) == 1) {
            answer -= mat[mat.length / 2][mat.length / 2];
        }

        return answer;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(arrayInts(ints(5)), 5),
                Arguments.of(ints(ints(1, 2, 3),
                                  ints(4, 5, 6),
                                  ints(7, 8, 9)), 25),
                Arguments.of(ints(ints(1, 1, 1, 1),
                                  ints(1, 1, 1, 1),
                                  ints(1, 1, 1, 1),
                                  ints(1, 1, 1, 1)), 8)
            );
        }
    }
}
