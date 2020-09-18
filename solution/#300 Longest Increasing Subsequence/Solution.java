package leetcode.q300.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/longest-increasing-subsequence/">
 * 300. Longest Increasing Subsequence
 * </a>
 */
class Solution {

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int answer = 1;
        // 第i个数字被选取的情况下，最大长度
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += 1; // 把当前数字选上
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(emptyInts(), 0),
                Arguments.of(ints(2), 1),
                Arguments.of(ints(0, 0, 0, 0, 0), 1),
                Arguments.of(ints(0, 0, 1, 0, 0), 2),
                Arguments.of(ints(0, 0, 1, 2, 0), 3),
                Arguments.of(ints(0, 0, 1, 0, 2), 3),
                Arguments.of(ints(10, 9, 2, 5, 3, 7, 101, 18), 4),
                Arguments.of(ints(10, 9, 2, 5, 3, 7, 4, 18), 4),
                Arguments.of(ints(10, 9, 2, 5, 9, 7, 4, 18), 4),
                Arguments.of(ints(10, 9, 2, 5, 9, 1, 4, 18), 4),
                Arguments.of(ints(10, 9, 2, 5, 9, 10, 4, 18), 5)
            );
        }
    }
}
