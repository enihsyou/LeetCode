package leetcode.q67.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

class Solution {

    @SuppressWarnings({ "CharUsedInArithmeticContext", "NumericCastThatLosesPrecision" })
    public String addBinary(String a, String b) {
        int lengthA = a.length(), lengthB = b.length();
        int pointA  = lengthA - 1, pointB = lengthB - 1;

        char[] adder  = new char[Math.max(lengthA, lengthB) + 1];
        int    insert = adder.length;

        int carry = 0;
        while (pointA >= 0 || pointB >= 0) {
            int oneA = pointA >= 0 ? a.charAt(pointA--) - '0' : 0;
            int oneB = pointB >= 0 ? b.charAt(pointB--) - '0' : 0;
            adder[--insert] = (char) ('0' + (oneA ^ oneB ^ carry));
            carry           = (oneA & oneB) | (carry & oneA) | (carry & oneB);
        }
        if (carry == 1) {
            adder[0] = '1';
            return new String(adder);
        } else {
            return new String(adder, 1, adder.length - 1);
        }
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of("11", "1", "100"),
                Arguments.of("1010", "1011", "10101"),
                Arguments.of("0", "0", "0"),
                Arguments.of("0", "1", "1"),
                Arguments.of("111", "1", "1000")
            );
        }
    }
}
