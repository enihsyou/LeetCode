package leetcode.q79.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/word-search/">
 * 79. Word Search
 * </a>
 */
class Solution {

    private static final int[][] directions = {
        new int[]{ 0, -1 },
        new int[]{ 0, +1 },
        new int[]{ -1, 0 },
        new int[]{ +1, 0 }
    };

    private char[][] board;
    private String   word;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word  = word;

        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (bfs(visited, row, col, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfs(boolean[][] visited, int iamX, int iamY, int pointer) {
        if (board[iamX][iamY] != word.charAt(pointer)) {
            // 不是目标值
            return false;
        }
        if (pointer == word.length() - 1) {
            // 是目标值，而且指针到底了
            return true;
        }
        visited[iamX][iamY] = true;
        for (int[] direction : directions) {
            int gotoX = iamX + direction[0];
            int gotoY = iamY + direction[1];
            if (gotoX < 0 || board.length == gotoX ||
                gotoY < 0 || board[0].length == gotoY) {
                // 忽略在边界之外坐标
                continue;
            }
            if (visited[gotoX][gotoY]) {
                // 忽略已经走过的路径点
                continue;
            }
            if (bfs(visited, gotoX, gotoY, pointer + 1)) {
                return true;
            }
        }
        visited[iamX][iamY] = false;
        return false;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            char[][] board1 = chars(chars('A', 'B', 'C', 'E'),
                                    chars('S', 'F', 'C', 'S'),
                                    chars('A', 'D', 'E', 'E'));
            char[][] board2 = chars(chars('A', 'B', 'C', 'E'),
                                    chars('S', 'F', 'E', 'S'),
                                    chars('A', 'D', 'E', 'E'));
            char[][] board3 = { chars('A') };
            return Stream.of(
                Arguments.of(board1, "ABCCED", true),
                Arguments.of(board1, "SEE", true),
                Arguments.of(board1, "C", true),
                Arguments.of(board1, "CS", true),
                Arguments.of(board1, "DECCE", true),
                Arguments.of(board1, "SCFS", true),
                Arguments.of(board1, "ABCB", false),
                Arguments.of(board1, "CEF", false),
                Arguments.of(board1, "ABCESCFSADEE", true),
                Arguments.of(board1, "ABCESCFSADEF", false),
                Arguments.of(board2, "ABCESEEEFS", true),
                Arguments.of(board3, "A", true),
                Arguments.of(board3, "B", false)
            );
        }
    }
}
