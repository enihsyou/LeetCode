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

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                if (bfs(visited, row, col, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfs(boolean[][] visited, int iamX, int iamY, int pointer) {
        if (pointer == word.length()) {
            // 到达目标
            return true;
        }
        if (iamX < 0 || board.length == iamX ||
            iamY < 0 || board[0].length == iamY) {
            // 忽略在边界之外坐标
            return false;
        }
        if (visited[iamX][iamY]) {
            // 忽略已经走过的路径点
            return false;
        }
        if (board[iamX][iamY] != word.charAt(pointer)) {
            // 结束目标值不匹配的路径
            return false;
        }

        // 访问这个路径点，继续搜索
        visited[iamX][iamY] = true;
        if (bfs(visited, iamX + 1, iamY, pointer + 1) ||
            bfs(visited, iamX - 1, iamY, pointer + 1) ||
            bfs(visited, iamX, iamY + 1, pointer + 1) ||
            bfs(visited, iamX, iamY - 1, pointer + 1)) {
            return true;
        }
        // 这个路径点走不通了，允许其他搜索继续使用这个路径点
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
            return Stream.of(
                Arguments.of(board1, "ABCCED", true),
                Arguments.of(board1, "SEE", true),
                Arguments.of(board1, "C", true),
                Arguments.of(board1, "CS", true),
                Arguments.of(board1, "DECCE", true),
                Arguments.of(board1, "SCFS", true),
                Arguments.of(board1, "ABCB", false),
                Arguments.of(board2, "ABCESEEEFS", true)
            );
        }
    }
}
