package leetcode.q100160.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/string-to-url-lcci/">
 * 面试题 01.03. String to URL LCCI
 * </a>
 */
class Solution {

    public String replaceSpaces(String S, int length) {
        char[] content = new char[S.length()];

        int p = 0;
        for (int i = 0; i < length; i++) {
            if (S.charAt(i) == ' ') {
                content[p++] = '%';
                content[p++] = '2';
                content[p++] = '0';
            } else {
                content[p++] = S.charAt(i);
            }
        }

        return new String(content, 0, p);
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of("Mr John Smith    ", 13, "Mr%20John%20Smith"),
                Arguments.of("               ", 5, "%20%20%20%20%20"),
                Arguments.of("ds sdfs afs sdfa dfssf asdf             ", 27, "ds%20sdfs%20afs%20sdfa%20dfssf%20asdf")
            );
        }
    }
}
