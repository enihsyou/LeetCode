package leetcode.q825.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline/">
 * 807. Max Increase to Keep City Skyline
 * </a>
 */
class Solution {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] verticle   = new int[grid.length];
        int[] horizontal = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                horizontal[i] = Math.max(horizontal[i], grid[i][j]);
                verticle[j]   = Math.max(verticle[j], grid[i][j]);
            }
        }
        int answer = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                answer += Math.min(horizontal[i], verticle[j]) - grid[i][j];
            }
        }
        return answer;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(ints(ints(3, 0, 8, 4),
                                  ints(2, 4, 5, 7),
                                  ints(9, 2, 6, 3),
                                  ints(0, 3, 1, 0)), 35)
            );
        }
    }
}
