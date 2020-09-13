package leetcode.q36.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/valid-sudoku/">
 * 36. Valid Sudoku
 * </a>
 */
class Solution {

    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][] grps = new int[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) { // 9 x 9
                int  k    = (i / 3) * 3 + j / 3;
                char cell = board[i][j];
                if (cell != '.') {
                    int cellV = (int) cell - (int) '0' - 1;
                    if (rows[i][cellV]++ > 0) {
                        return false;
                    }
                    if (cols[j][cellV]++ > 0) {
                        return false;
                    }
                    if (grps[k][cellV]++ > 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(chars(chars('5', '3', '.', '.', '7', '.', '.', '.', '.'),
                                   chars('6', '.', '.', '1', '9', '5', '.', '.', '.'),
                                   chars('.', '9', '8', '.', '.', '.', '.', '6', '.'),
                                   chars('8', '.', '.', '.', '6', '.', '.', '.', '3'),
                                   chars('4', '.', '.', '8', '.', '3', '.', '.', '1'),
                                   chars('7', '.', '.', '.', '2', '.', '.', '.', '6'),
                                   chars('.', '6', '.', '.', '.', '.', '2', '8', '.'),
                                   chars('.', '.', '.', '4', '1', '9', '.', '.', '5'),
                                   chars('.', '.', '.', '.', '8', '.', '.', '7', '9')), true),
                Arguments.of(chars(chars('8', '3', '.', '.', '7', '.', '.', '.', '.'),
                                   chars('6', '.', '.', '1', '9', '5', '.', '.', '.'),
                                   chars('.', '9', '8', '.', '.', '.', '.', '6', '.'),
                                   chars('8', '.', '.', '.', '6', '.', '.', '.', '3'),
                                   chars('4', '.', '.', '8', '.', '3', '.', '.', '1'),
                                   chars('7', '.', '.', '.', '2', '.', '.', '.', '6'),
                                   chars('.', '6', '.', '.', '.', '.', '2', '8', '.'),
                                   chars('.', '.', '.', '4', '1', '9', '.', '.', '5'),
                                   chars('.', '.', '.', '.', '8', '.', '.', '7', '9')), false)
            );
        }
    }
}
