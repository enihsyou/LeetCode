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

    private char[][] board;
    private String   word;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word  = word;

        int rows = board.length;
        int cols = board[0].length;

        boolean[][] visited = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (bfs(visited, row, col, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfs(boolean[][] visited, int iamX, int iamY, int pointer) {
        if (pointer == word.length()) {
            // 是目标值，而且指针到底了
            return true;
        }
        if (iamX < 0 || board.length == iamX ||
            iamY < 0 || board[0].length == iamY) {
            // 忽略在边界之外坐标
            return false;
        }
        if (board[iamX][iamY] != word.charAt(pointer)) {
            // 不是目标值
            return false;
        }
        if (visited[iamX][iamY]) {
            // 访问过的路径点
            return false;
        }
        visited[iamX][iamY] = true;
        boolean found = bfs(visited, iamX + 1, iamY, pointer + 1) ||
                        bfs(visited, iamX - 1, iamY, pointer + 1) ||
                        bfs(visited, iamX, iamY + 1, pointer + 1) ||
                        bfs(visited, iamX, iamY - 1, pointer + 1);
        visited[iamX][iamY] = false;
        return found;
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
