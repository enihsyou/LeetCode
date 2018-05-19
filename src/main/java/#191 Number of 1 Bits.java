import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

class Q191_Number_of_1_Bits {

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

    public static class SolutionTest {

        @Rule
        public final JUnitSoftAssertions soft = new JUnitSoftAssertions();

        private final Q191_Number_of_1_Bits solution = new Q191_Number_of_1_Bits();

        @Test
        public void test() {
            soft.assertThat(solution.hammingWeight(11)).isEqualTo(3);
            soft.assertThat(solution.hammingWeight(128)).isEqualTo(1);
            soft.assertThat(solution.hammingWeight(0)).isEqualTo(0);
            soft.assertThat(solution.hammingWeight(-1)).isEqualTo(32);
        }
    }
}
