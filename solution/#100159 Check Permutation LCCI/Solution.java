package leetcode.q100159.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/check-permutation-lcci/">
 *     面试题 01.02. Check Permutation LCCI
 * </a>
 */
class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] letters = new int[256];

        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i)] += 1;
        }

        for (int i = 0; i < s2.length(); i++) {
            if ((letters[s2.charAt(i)] -= 1) < 0) {
                return false;
            }
        }

        return true;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of("abc", "bca", true),
                Arguments.of("abc", "bad", false)
            );
        }
    }
}
