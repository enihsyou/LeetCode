package leetcode.q190.java;

import java.util.stream.Stream;

import leetcode.base.java.JavaTest;
import org.junit.jupiter.params.provider.Arguments;

/**
 * <a href="https://leetcode-cn.com/problems/reverse-bits/">
 * 190. Reverse Bits
 * </a>
 */
public class Solution {

    // you need treat n as an unsigned value
    @SuppressWarnings("PointlessBitwiseExpression")
    public int reverseBits(int n) {
        n = (n & 0b10101010101010101010101010101010) >>> 0x01 |
            (n & 0b01010101010101010101010101010101) << 0x01;
        n = (n & 0b11001100110011001100110011001100) >>> 0x02 |
            (n & 0b00110011001100110011001100110011) << 0x02;
        n = (n & 0b11110000111100001111000011110000) >>> 0x04 |
            (n & 0b00001111000011110000111100001111) << 0x04;
        n = (n & 0b11111111000000001111111100000000) >>> 0x08 |
            (n & 0b00000000111111110000000011111111) << 0x08;
        n = (n & 0b11111111111111111111111111111111) >>> 0x10 |
            (n & 0b11111111111111111111111111111111) << 0x10;
        return n;
    }

    static class SolutionTest extends JavaTest<Solution> {

        @Override
        protected Stream<Arguments> provider() {
            return Stream.of(
                Arguments.of(0b00000010100101000001111010011100,
                             0b00111001011110000010100101000000),
                Arguments.of(0b11111111111111111111111111111101,
                             0b10111111111111111111111111111111)
            );
        }
    }
}
