package leetcode.q405.java;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class Solution {

    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        while (num != 0) {
            int i = num & 0xf;
            switch (i) {
                case 0x0:
                case 0x1:
                case 0x2:
                case 0x3:
                case 0x4:
                case 0x5:
                case 0x6:
                case 0x7:
                case 0x8:
                case 0x9:
                    sb.append(i);
                    break;
                case 0xa:
                    sb.append('a');
                    break;
                case 0xb:
                    sb.append('b');
                    break;
                case 0xc:
                    sb.append('c');
                    break;
                case 0xd:
                    sb.append('d');
                    break;
                case 0xe:
                    sb.append('e');
                    break;
                case 0xf:
                    sb.append('f');
                    break;
            }
            num >>= 4;

            if (sb.length() == 8) {
                break;
            }
        }

        return sb.reverse().toString();
    }

    static class SolutionTest {

        private final Solution solution = new Solution();

        @Test
        void test() {
            SoftAssertions.assertSoftly(soft -> {
                soft.assertThat(solution.toHex(-1)).isEqualTo("ffffffff");
                soft.assertThat(solution.toHex(26)).isEqualTo("1a");
            });
        }
    }
}

