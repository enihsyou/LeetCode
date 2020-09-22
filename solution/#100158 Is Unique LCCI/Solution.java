package leetcode.q100158.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/is-unique-lcci/">
 * 面试题 01.01. Is Unique LCCI
 * </a>
 */
class Solution {

    public boolean isUnique(String astr) {
        if (astr.length() > 256) return false;
        int[] bits = { 0, 0, 0, 0, 0, 0, 0, 0 }; // 256bit

        for (int i = 0; i < astr.toCharArray().length; i++) {
            int word = astr.charAt(i);

            int group = word / Integer.SIZE;
            int place = word % Integer.SIZE;
            if ((bits[group] >> place & 1) == 0) {
                bits[group] |= 1 << place;
            } else {
                return false;
            }
        }
        return true;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of("", true),
                Arguments.of("a", true),
                Arguments.of("ab", true),
                Arguments.of("aa", false),
                Arguments.of("abc", true),
                Arguments.of("leetcode", false),
                Arguments.of("iluhwpyk", true)
            );
        }
    }
}
