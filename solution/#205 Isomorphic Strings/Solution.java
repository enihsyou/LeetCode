package leetcode.q205.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/isomorphic-strings/">
 * 205. Isomorphic Strings
 * </a>
 */
class Solution {

    public boolean isIsomorphic(String s, String t) {
        return s.length() == t.length() &&
               isIsomorphicHelper(s, t) && isIsomorphicHelper(t, s);
    }

    private boolean isIsomorphicHelper(CharSequence s, CharSequence t) {
        char[] mapping = new char[128]; // a -> b
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);

            if (mapping[a] == '\0') {
                mapping[a] = b;
            } else {
                if (mapping[a] != b) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        int   n    = s.length();
        int[] mapS = new int[128];
        int[] mapT = new int[128];
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (mapS[c1] == mapT[c2]) {
                //当前的映射值是否相同
                if (mapS[c1] == 0) {
                    //是否已经修改过，修改过就不需要再处理
                    mapS[c1] = i + 1;
                    mapT[c2] = i + 1;
                }
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
                Arguments.of("ab", "aa", false),
                Arguments.of("egg", "add", true),
                Arguments.of("foo", "bar", false),
                Arguments.of("paper", "title", true)
            );
        }
    }
}
