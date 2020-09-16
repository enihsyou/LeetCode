package leetcode.q100345.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/qiu-12n-lcof/">
 * 剑指 Offer 64. 求1+2+…+n LCOF
 * </a>
 */
class Solution {

    public int sumNums(int n) {
        boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(3, 6),
                Arguments.of(9, 45)
            );
        }
    }
}
