package leetcode.q792.java;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class Solution {

    public int search(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        while (head <= tail) {
            int cursor  = (head + tail) / 2;
            int compare = nums[cursor] - target;
            if (compare == 0) {
                return cursor;
            }
            if (compare > 0) {
                tail = cursor - 1;
            } else {
                head = cursor + 1;
            }
        }
        return -1;
    }

    static class SolutionTest {

        private final Solution solution = new Solution();

        @Test
        void test() {
            SoftAssertions.assertSoftly(soft -> {
                soft.assertThat(solution.search(new int[]{ -1, 0, 3, 5, 9, 12 }, 12)).isEqualTo(5);
                soft.assertThat(solution.search(new int[]{ -1, 0, 3, 5, 9, 12 }, -1)).isEqualTo(0);
                soft.assertThat(solution.search(new int[]{ -1, 0, 3, 5, 9, 12 }, 9)).isEqualTo(4);
                soft.assertThat(solution.search(new int[]{ -1, 0, 3, 5, 9, 12 }, 2)).isEqualTo(-1);
                soft.assertThat(solution.search(new int[]{ 0 }, 0)).isEqualTo(0);
                soft.assertThat(solution.search(new int[]{ 0 }, 1)).isEqualTo(-1);
            });
        }
    }
}
