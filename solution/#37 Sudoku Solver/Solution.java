package leetcode.q37.java;

import java.util.stream.Stream;

import leetcode.base.java.AssertMode;
import leetcode.base.java.ExecutionOption;
import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/sudoku-solver/">
 * 37. Sudoku Solver
 * </a>
 */
@SuppressWarnings({ "CharUsedInArithmeticContext", "NumericCastThatLosesPrecision" })
class Solution {

    private short[] rows;
    private short[] cols;
    private short[] recs;

    public void solveSudoku(char[][] board) {
        this.rows = new short[board.length];
        this.cols = new short[board.length];
        this.recs = new short[board.length];

        hashBoard(board);
        solveBoard(board);
    }

    private void bitset(int i, int j, int num) {
        int position = 1 << num;
        rows[i] |=                 position;
        cols[j] |=                 position;
        recs[i / 3 * 3 + j / 3] |= position;
    }

    private void bitmask(int i, int j, int num) {
        int position = 1 << num;
        rows[i] ^=                 position;
        cols[j] ^=                 position;
        recs[i / 3 * 3 + j / 3] ^= position;
    }

    private int bithole(int i, int j) {
        int row = rows[i];
        int col = cols[j];
        int rec = recs[i / 3 * 3 + j / 3];
        return ~row & ~col & ~rec & 0x1ff;
    }

    private void hashBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int num = board[i][j] - '1';
                if (num >= 0) {
                    bitset(i, j, num);
                }
            }
        }
    }

    private boolean solveBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for (int p = 0, candidates = bithole(i, j);
                    p < board.length && candidates > 0;
                    p++, candidates >>= 1) {
                    if ((candidates & 1) == 1) {
                        bitset(i, j, p);
                        board[i][j] = (char) ('1' + p);
                        if (solveBoard(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                        bitmask(i, j, p);
                    }
                }
                // 在一个需要解的空位没找到解
                return false;
            }
        }
        // 所有位置都走过了 有数字
        return true;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected void tweakExecutionOption(ExecutionOption option) {
            option.assertMode(AssertMode.exceptArgumentMode(0));
        }

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
                                   chars('.', '.', '.', '.', '8', '.', '.', '7', '9')),
                             chars(chars('5', '3', '4', '6', '7', '8', '9', '1', '2'),
                                   chars('6', '7', '2', '1', '9', '5', '3', '4', '8'),
                                   chars('1', '9', '8', '3', '4', '2', '5', '6', '7'),
                                   chars('8', '5', '9', '7', '6', '1', '4', '2', '3'),
                                   chars('4', '2', '6', '8', '5', '3', '7', '9', '1'),
                                   chars('7', '1', '3', '9', '2', '4', '8', '5', '6'),
                                   chars('9', '6', '1', '5', '3', '7', '2', '8', '4'),
                                   chars('2', '8', '7', '4', '1', '9', '6', '3', '5'),
                                   chars('3', '4', '5', '2', '8', '6', '1', '7', '9')))
            );
        }
    }
}
