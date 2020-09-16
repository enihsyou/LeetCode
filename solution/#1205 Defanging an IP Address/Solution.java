package leetcode.q1205.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/defanging-an-ip-address/">
 *     1108. Defanging an IP Address
 * </a>
 */
class Solution {
    public String defangIPaddr(String address) {
        StringBuilder answer = new StringBuilder(address.length() + 9);
        for (int i = 0; i < address.length(); i++) {
            char c = address.charAt(i);
            if (c == '.') {
                answer.append("[.]");
            } else {
                answer.append(c);
            }
        }
        return answer.toString();
    }

    public String defangIPaddr2(String address) {
        return address.replace(".", "[.]");
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of("1.1.1.1", "1[.]1[.]1[.]1"),
                Arguments.of("255.100.50.0", "255[.]100[.]50[.]0")
            );
        }
    }
}
