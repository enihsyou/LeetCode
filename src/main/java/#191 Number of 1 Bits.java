import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

/**
 * #191 Number of 1 Bits
 */
class Q191 {

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

}

 class Q191Test {

    private final Q191 solution = new Q191();

    @Test
     void test() {
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(solution.hammingWeight(11)).isEqualTo(3);
            soft.assertThat(solution.hammingWeight(128)).isEqualTo(1);
            soft.assertThat(solution.hammingWeight(0)).isEqualTo(0);
            soft.assertThat(solution.hammingWeight(-1)).isEqualTo(32);
        });
    }
}
