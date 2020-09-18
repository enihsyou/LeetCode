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
        int answer = 0;
        boolean[] primes = new boolean[n]; // excluded number
        for (int i = 2; i < n; i++) {
            if (primes[i]) {
                continue;
            }
            answer++;
            int num = i + i;
            while (num < primes.length) {
                primes[num] = true;
                num += i;
            }
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
                Arguments.of(12, 5)
            );
        }
    }
}
