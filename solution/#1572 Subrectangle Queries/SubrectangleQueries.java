package leetcode.q1572.java;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * <a href="https://leetcode-cn.com/problems/subrectangle-queries/">
 * 1476. Subrectangle Queries
 * </a>
 */
@SuppressWarnings({ "WeakerAccess", "PublicConstructorInNonPublicClass" })
class SubrectangleQueries {

    private final int[][] rectangle;

    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                rectangle[i][j] = newValue;
            }
        }
    }

    public int getValue(int row, int col) {
        return rectangle[row][col];
    }

    static class SolutionTest {

        @Test
        void case1() {
            SubrectangleQueries obj = new SubrectangleQueries(
                ints(ints(1, 2, 1), ints(4, 3, 4), ints(3, 2, 1), ints(1, 1, 1)));
            assertThat(obj.getValue(0, 2)).isEqualTo(1);

            obj.updateSubrectangle(0, 0, 3, 2, 5);
            assertThat(obj.getValue(0, 2)).isEqualTo(5);
            assertThat(obj.getValue(3, 1)).isEqualTo(5);

            obj.updateSubrectangle(3, 0, 3, 2, 10);
            assertThat(obj.getValue(3, 1)).isEqualTo(10);
            assertThat(obj.getValue(0, 2)).isEqualTo(5);
        }

        @Test
        void case2() {
            SubrectangleQueries obj = new SubrectangleQueries(
                ints(ints(1, 1, 1), ints(2, 2, 2), ints(3, 3, 3)));
            assertThat(obj.getValue(0, 0)).isEqualTo(1);

            obj.updateSubrectangle(0, 0, 2, 2, 100);
            assertThat(obj.getValue(0, 0)).isEqualTo(100);
            assertThat(obj.getValue(2, 2)).isEqualTo(100);

            obj.updateSubrectangle(1, 1, 2, 2, 20);
            assertThat(obj.getValue(2, 2)).isEqualTo(20);
        }

        protected static int[] ints(int... ints) {
            return ints;
        }

        protected static int[][] ints(int[]... ints) {
            return ints;
        }

    }
}
