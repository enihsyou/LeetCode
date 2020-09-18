package leetcode.q204.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/count-primes/">
 * 204. Count Primes
 * </a>
 */
class Solution {

    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] excludes = new boolean[n]; // excluded number
        for (int i = 2; i * i < n; i++) {
            if (excludes[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += i) {
                excludes[j] = true;
            }
        }
        int answer = 0;
        for (int i = 2; i < n; i++) {
            if (!excludes[i]) answer++;
        }
        return answer;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, 0),
                Arguments.of(2, 0),
                Arguments.of(3, 1),
                Arguments.of(10, 4),
                Arguments.of(11, 4),
                Arguments.of(12, 5),
                Arguments.of(499979, 41537),
                Arguments.of(999983, 78497),
                Arguments.of(1500000, 114155)
            );
        }
    }
}
