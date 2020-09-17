package leetcode.q1610.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/xor-operation-in-an-array/">
 * 1486. XOR Operation in an Array
 * </a>
 */
class Solution {

    public int xorOperation(int n, int start) {
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer ^= start + 2 * i;
        }
        return answer;
    }

    public int xorOperation2(int n, int start) {
        int answer = 2 * xor(n, start / 2);
        if (isEven(n & start)) {
            return answer;
        } else {
            return answer + 1;
        }
    }

    /**
     * 因为2a的最后一位是0，加一不改变其他位，所以有
     * {@code 2a ^ 2a+1 = 1}
     * {@code 2a ^ 2a+1 ^ 2a+2 = 1 ^ 2a+2}
     * {@code 2a ^ 2a+1 ^ 2a+2 ^ 2a+3 = 1}
     */
    private static int xor(int n, int start) {
        if (isEven(start)) {
            return helper(n, start);
        } else {
            return helper(n + 1, start - 1) ^ (start - 1);
        }
    }

    /**
     * 因为2a的最后一位是0，加一不改变其他位，所以有
     * {@code 2a ^ 2a+1 = 1}
     * {@code 2a ^ 2a+1 ^ 2a+2 = 1 ^ 2a+2}
     * {@code 2a ^ 2a+1 ^ 2a+2 ^ 2a+3 = 1}
     * @param halfStart 是个偶数
     */
    private static int helper(int n, int halfStart) {
        if (isEven(n)) {
            return n / 2 & 1;
        } else {
            return n / 2 & 1 ^ (halfStart + n - 1);
        }
    }

    private static boolean isEven(int n) {
        return (n & 1) == 0;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(5, 0, 8),
                Arguments.of(4, 3, 8),
                Arguments.of(1, 7, 7),
                Arguments.of(10, 5, 2)
            );
        }
    }
}
