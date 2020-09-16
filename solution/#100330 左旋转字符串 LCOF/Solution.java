package leetcode.q100330.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/">
 * 剑指 Offer 58 - II. 左旋转字符串 LCOF
 * </a>
 */
class Solution {

    public String reverseLeftWords(String s, int n) {
        //noinspection StringBufferReplaceableByString
        StringBuilder builder = new StringBuilder(s.length());
        builder.append(s, n, s.length());
        builder.append(s, 0, n);
        return builder.toString();
    }

    public String reverseLeftWords2(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of("abcdefg", 2, "cdefgab"),
                Arguments.of("lrloseumgh", 6, "umghlrlose")
            );
        }
    }
}
